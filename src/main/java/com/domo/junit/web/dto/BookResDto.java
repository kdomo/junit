package com.domo.junit.web.dto;


import com.domo.junit.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@Getter
@NoArgsConstructor
public class BookResDto {
    private Long id;
    private String title;
    private String author;

    public BookResDto toResDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        return this;
    }
}
