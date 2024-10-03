package com.example.jdbc.service;

import com.example.jdbc.entity.Book;
import com.example.jdbc.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id);
    }
    public void createBook(Book book){
      bookRepository.save(book);
    }
    public void updateBook(Book book){
        bookRepository.update(book);
    }
    public void deleteBook(Long id){
        bookRepository.delete(id);
    }
}
