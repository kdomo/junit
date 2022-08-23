package com.domo.junit.service;

import com.domo.junit.domain.Book;
import com.domo.junit.repository.BookRepository;
import com.domo.junit.web.dto.BookReqDto;
import com.domo.junit.web.dto.BookResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    //1.책 등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResDto 책_등록(BookReqDto bookReqDto){
        Book bookPS = bookRepository.save(bookReqDto.toEntity());
        return new BookResDto().toResDto(bookPS);
    }
    //2.책 목록보기
    public List<BookResDto> 책_목록보기(){
        return bookRepository.findAll().stream()
                .map(new BookResDto()::toResDto).collect(Collectors.toList());
    }
    //4.책 삭제

    //5.책 수정
}
