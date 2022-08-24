package com.domo.junit.service;

import com.domo.junit.repository.BookRepository;
import com.domo.junit.web.dto.BookReqDto;
import com.domo.junit.web.dto.BookResDto;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

//        assertEquals(bookResDto.getTitle(),"title");
    }
}