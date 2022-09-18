package com.domo.junit.controller;

import com.domo.junit.common.ResultDto;
import com.domo.junit.service.BookService;
import com.domo.junit.controller.request.BookReqDto;
import com.domo.junit.controller.response.BookResDto;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookApiController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<ResultDto> getBookList(){
        List<BookResDto> list = bookService.getBookList();
        return new ResponseEntity<>(ResultDto.builder().code(1).msg("조회 성공").body(list).build(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDto> getBookList(@PathVariable Long id){
        BookResDto book = bookService.getBook(id);
        return new ResponseEntity<>(ResultDto.builder().code(1).msg("조회 성공").body(book).build(),HttpStatus.OK);
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
        BookResDto BookResDto = bookService.save(bookReqDto);
        return new ResponseEntity<>(ResultDto.builder().code(1).msg("등록 성공").body(BookResDto).build(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultDto> bookDelete(@PathVariable Long id){
        bookService.delete(id);
        return new ResponseEntity<>(ResultDto.builder().code(1).msg("삭제 성공").build(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResultDto> bookUpdate(@PathVariable Long id, @RequestBody @Valid BookReqDto bookReqDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError fe : bindingResult.getFieldErrors()){
                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }
            throw new RuntimeException(errorMap.toString());
        }
        BookResDto bookResDto =  bookService.update(id, bookReqDto);

        return  new ResponseEntity<>(ResultDto.builder().code(1).msg("수정 성공").body(bookResDto).build(),HttpStatus.OK);

    }


}
