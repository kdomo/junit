package com.domo.junit.web;

import com.domo.junit.service.BookService;
import com.domo.junit.web.dto.BookReqDto;
import com.domo.junit.web.dto.BookResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookApiController {
    private BookService bookService;

    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookResDto>> getList(){
        List<BookResDto> list = bookService.책_목록보기();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<BookResDto> 책_등록(BookReqDto bookReqDto){
        BookResDto BookResDto = bookService.책_등록(bookReqDto);
        return new ResponseEntity<>(BookResDto, HttpStatus.OK);
    }


}
