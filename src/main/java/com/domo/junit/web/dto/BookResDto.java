package com.domo.junit.web.dto;


import com.domo.junit.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@Getter
@NoArgsConstructor
public class BookResDto {
    private Long id;
    private String title;
    private String author;

    @Builder
    public BookResDto(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}
