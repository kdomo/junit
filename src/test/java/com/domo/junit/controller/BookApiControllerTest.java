package com.domo.junit.controller;


import com.domo.junit.controller.request.BookReqDto;
import com.domo.junit.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BookApiControllerTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private TestRestTemplate rt;

    private static ObjectMapper om;
    private static HttpHeaders headers;

    @BeforeAll
    static void init(){
        om = new ObjectMapper();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @DisplayName("책 저장하기 테스트")
    void saveBook() throws Exception {
        //given
        BookReqDto bookReqDto = new BookReqDto();
        bookReqDto.setTitle("책 제목");
        bookReqDto.setAuthor("책 작가");
        String body = om.writeValueAsString(bookReqDto);

        //when
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = rt.exchange("/api/v1/books", HttpMethod.POST, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        String title = dc.read("$.body.title");
        String author = dc.read("$.body.author");

        assertThat(title).isEqualTo("책 제목");
        assertThat(author).isEqualTo("책 작가");


    }

    @Test
    @DisplayName("책 목록보기 테스트")
    void bookList(){
        //given

        //when
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = rt.exchange("/api/v1/books", HttpMethod.GET, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        int code = dc.read("$.code");
        assertThat(code).isEqualTo(1);
    }

    @Test
    @DisplayName("책 한권보기 테스트")
    void getBookOne(){
        //given
        Long id = 1L;

        //when
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = rt.exchange("/api/v1/books/" + id, HttpMethod.GET, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        int code = dc.read("$.code");
        String title = dc.read("$.body.title");
        String author = dc.read("$.body.author");

        assertThat(code).isEqualTo(1);
        assertThat(title).isEqualTo("책 제목");
        assertThat(author).isEqualTo("책 작가");
    }

    @Test
    @DisplayName("책 삭제하기 테스트")
    void deleteBook(){
        //given
        Long id = 1L;
        BookReqDto book = new BookReqDto();
        book.setTitle("title");
        book.setAuthor("author");
        bookService.save(book);
        //when
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = rt.exchange("/api/v1/books/" + id, HttpMethod.DELETE, request, String.class);
        DocumentContext dc = JsonPath.parse(response.getBody());
        int code = dc.read("$.code");

        //then
        System.out.println("code : " + code);
        assertThat(code).isEqualTo(1);

    }

}