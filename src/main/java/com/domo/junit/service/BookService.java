package com.domo.junit.service;

import com.domo.junit.domain.Book;
import com.domo.junit.repository.BookRepository;
import com.domo.junit.controller.request.BookReqDto;
import com.domo.junit.controller.response.BookResDto;
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
    public BookResDto save(BookReqDto bookReqDto){
        Book bookPS = bookRepository.save(bookReqDto.toEntity());
        return bookPS.toResDto();
    }
    //2.책 목록보기
    public List<BookResDto> getBookList(){
        return bookRepository.findAll().stream()
                .map(book -> book.toResDto())
                .collect(Collectors.toList());
    }

    //3.책 한건건보기
    public BookResDto getBook(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get().toResDto();
        }else{
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }
   //4.책 삭제
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Long id){
        bookRepository.deleteById(id);
    }
    //5.책 수정
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResDto update(Long id, BookReqDto bookReqDto){
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
