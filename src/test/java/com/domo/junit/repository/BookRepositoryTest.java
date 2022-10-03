package com.domo.junit.repository;

import com.domo.junit.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("dev")
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void 데이터_준비(){
        Book book = Book.builder()
                .title("junit5")
                .author("domo")
                .build();
        bookRepository.save(book);
    }
    //1.책 등록
    @Test
    void 책_등록_테스트(){
        //given
        Book book = Book.builder()
                .title("junit5")
                .author("domo")
                .build();

        //when
        Book bookPS = bookRepository.save(book);

        //then
        assertThat(bookPS.getTitle()).isEqualTo("junit5");
        assertThat(bookPS.getAuthor()).isEqualTo("domo");
    }

    //2.책 목록 보기
    @Test
    void 책_목록_보기(){
        //given

        //when
        List<Book> booksPS = bookRepository.findAll();
        //then
        assertThat(booksPS.size()).isEqualTo(1);
    }
    //3.책 한건 보기
    @Test
    @Sql("classpath:db/tableInit.sql")
    void 책_한건_보기(){
        //given

        //when
        Book bookPS = bookRepository.findById(1L).get();
        //then
        assertThat(bookPS.getTitle()).isEqualTo("junit5");
        assertThat(bookPS.getAuthor()).isEqualTo("domo");
    }

    //4.책 삭제
    @Test
    @Sql("classpath:db/tableInit.sql")
    void 책_삭제(){
        //when
        bookRepository.deleteById(1L);
        //then
//        assertFalse(bookRepository.findById(1L).isPresent());
        assertThat(bookRepository.findById(1L).isPresent()).isFalse();
    }
    //5.책 수정
    @Test
    @Sql("classpath:db/tableInit.sql")
    void 책_수정(){
        //given
        //when
        Book bookPS = bookRepository.save(new Book(1L,"junit","kdomo"));
        bookRepository.findAll().stream()
                .forEach(book -> {
                    System.out.println(book.toString());
                });
        //then
        assertThat(bookRepository.findById(1L).get().getTitle()).isNotEqualTo("junit5");
    }
}