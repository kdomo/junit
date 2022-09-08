package com.domo.junit.controller.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
