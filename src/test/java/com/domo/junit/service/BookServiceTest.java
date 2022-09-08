package com.domo.junit.service;

import com.domo.junit.domain.Book;
import com.domo.junit.repository.BookRepository;
import com.domo.junit.controller.request.BookReqDto;
import com.domo.junit.controller.response.BookResDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void 책_등록_테스트(){
        BookReqDto dto = new BookReqDto();
        dto.setTitle("title");
        dto.setAuthor("author");

        // stub
        when(bookRepository.save(any())).thenReturn(dto.toEntity());

        BookResDto bookResDto = bookService.책_등록(dto);
        assertThat(bookResDto.getTitle()).isEqualTo(dto.getTitle());
    }

    @Test
    void 책_목록보기(){
        List<Book> books = Arrays.asList(
                new Book(1L,"junit5","domo"),
                new Book(2L,"junit","domo1")
        );
        when(bookRepository.findAll()).thenReturn(books);


        List<BookResDto> bookResDtoList = bookService.책_목록보기();


        assertThat(bookResDtoList.get(0).getTitle()).isEqualTo("junit5");
        assertThat(bookResDtoList.get(0).getAuthor()).isEqualTo("domo");
        assertThat(bookResDtoList.get(1).getTitle()).isEqualTo("junit");
        assertThat(bookResDtoList.get(1).getAuthor()).isEqualTo("domo1");

    }

    @Test
    void 책_한건보기(){

        when(bookRepository.findById(any()))
                .thenReturn(Optional.of(new Book(1L, "junit5", "domo")));

        BookResDto dto = bookService.책_한건보기(1L);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getTitle()).isEqualTo("junit5");
        assertThat(dto.getAuthor()).isEqualTo("domo");

    }

    @Test
    void 책_수정하기(){
        //given
        BookReqDto bookReqDto = new BookReqDto();
        bookReqDto.setTitle("수정 후 title");
        bookReqDto.setAuthor("수정 후 author");

        //stub
        when(bookRepository.findById(any()))
                .thenReturn(Optional.of(new Book(1L, "수정 전 title", "수정 전 author")));

        //when
        BookResDto dto = bookService.책_수정(1L,bookReqDto);

        //then
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getTitle()).isEqualTo("수정 후 title");
        assertThat(dto.getAuthor()).isEqualTo("수정 후 author");
    }
}