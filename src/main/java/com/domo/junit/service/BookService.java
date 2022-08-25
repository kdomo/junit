package com.domo.junit.service;

import com.domo.junit.domain.Book;
import com.domo.junit.repository.BookRepository;
import com.domo.junit.web.dto.BookReqDto;
import com.domo.junit.web.dto.BookResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    //1.책 등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResDto 책_등록(BookReqDto bookReqDto){
        Book bookPS = bookRepository.save(bookReqDto.toEntity());
        return bookPS.toResDto();
    }
    //2.책 목록보기
    public List<BookResDto> 책_목록보기(){
        return bookRepository.findAll().stream()
                .map(book -> book.toResDto())
                .collect(Collectors.toList());
    }

    //3.책 한건건보기
    public BookResDto 책_한건보기(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get().toResDto();
        }else{
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }
   //4.책 삭제
    @Transactional(rollbackFor = RuntimeException.class)
    public void 책_삭제(Long id){
        bookRepository.deleteById(id);
    }
    //5.책 수정
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResDto 책_수정(Long id,BookReqDto bookReqDto){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.update(bookReqDto.getTitle(), bookReqDto.getAuthor());
            return book.toResDto();
        }else{
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }
}
