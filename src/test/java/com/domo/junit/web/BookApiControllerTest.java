package com.domo.junit.web;

import com.domo.junit.domain.Book;
import com.domo.junit.service.BookService;
import com.domo.junit.web.dto.BookResDto;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BookApiController.class)
class BookApiControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    BookService bookService;

    @Test
    @DisplayName("책 목록 가져오기")
    void getList() throws Exception {
        List<BookResDto> list = new ArrayList<>();
        list.add(BookResDto.builder().title("testTitle").author("testAuthor").build());

        given(bookService.책_목록보기()).willReturn(list);

        mvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("testTitle")));


    }
}