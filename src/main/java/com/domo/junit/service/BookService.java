package com.domo.junit.service;

import com.domo.junit.domain.Book;
import com.domo.junit.repository.BookRepository;
import com.domo.junit.web.dto.BookReqDto;
import com.domo.junit.web.dto.BookResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    //1.책 등록
    private final BookRepository bookRepository;
    //2.책 목록보기
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResDto 책_등록(BookReqDto bookReqDto){
        Book bookPS = bookRepository.save(bookReqDto.toEntity());
        return new BookResDto().toResDto(bookPS);
    }
    //3. 책 한건 보기

    //4.책 삭제

    //5.책 수정
}
