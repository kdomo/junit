package com.domo.junit.controller;

import com.domo.junit.common.ResultDto;
import com.domo.junit.service.BookService;
import com.domo.junit.controller.request.BookReqDto;
import com.domo.junit.controller.response.BookResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookApiController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResDto>> getBookList(){
        List<BookResDto> list = bookService.책_목록보기();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ResultDto> saveBook(@RequestBody @Valid BookReqDto bookReqDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError fe : bindingResult.getFieldErrors()){
                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }
            throw new RuntimeException(errorMap.toString());
        }
        BookResDto BookResDto = bookService.책_등록(bookReqDto);
        return new ResponseEntity<>(ResultDto.builder().code(1).msg("책 등록 성공").body(BookResDto).build(), HttpStatus.CREATED);
    }

//    public ResponseEntity<List<BookResDto>> getBook(){}
//
//    public ResponseEntity<List<BookResDto>> getBook(){}
//
//    public ResponseEntity<List<BookResDto>> getBook(){}


}
