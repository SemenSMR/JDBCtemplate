package com.example.jdbc.controller;

import com.example.jdbc.entity.Book;
import com.example.jdbc.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    public final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(){
        List<Book> books = bookService.getAllBook();
        return ResponseEntity.ok(books);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }
    @PostMapping
     public ResponseEntity<Book> createBook( @RequestBody Book book){
        bookService.createBook(book);
        return ResponseEntity.ok(book);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
        book.setId(id);
        bookService.updateBook(book);
      return ResponseEntity.ok(book);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
