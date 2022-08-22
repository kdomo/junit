package com.domo.junit.repository;

import com.domo.junit.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;


    //1.책 등록
    @Test
    void 책등록테스트(){
        //given
        Book book = Book.builder()
                .title("junit5")
                .author("domo")
                .build();

        //when
        Book bookPS = bookRepository.save(book);
        assertEquals("junit5",bookPS.getTitle());
        assertEquals("domo",bookPS.getAuthor());

        //then

    }

    //2.책 목록 보기

    //3.책 한건 보기

    //4.책 수정

    //5.책 삭제
}