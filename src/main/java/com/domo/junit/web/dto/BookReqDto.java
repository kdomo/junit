package com.domo.junit.web.dto;

import com.domo.junit.domain.Book;

public class BookReqDto {
    private String title;
    private String author;

    public Book toEntity(){
        return Book.builder().title(title).author(author).build();
    }
}
