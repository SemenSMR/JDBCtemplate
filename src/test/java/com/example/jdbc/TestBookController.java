package com.example.jdbc;

import com.example.jdbc.entity.Book;
import com.example.jdbc.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestBookController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPublicationYear(2024);
    }
    @Test
    void testGetAllBooks() throws Exception{
        List<Book> books = List.of(book);
        String bookJson = objectMapper.writeValueAsString(books);
        Mockito.when(bookService.getAllBook()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/book")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(bookJson));
    }
    @Test
    void testGetBookById() throws Exception {
        Mockito.when(bookService.getBookById(1L)).thenReturn(book);
        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/book/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(bookJson));
    }
    @Test
    void testCreateBook() throws Exception {
        Book newBook = new Book();
        newBook.setTitle("New Book");
        newBook.setAuthor("New Author");
        newBook.setPublicationYear(2023);

        String newBookJson = objectMapper.writeValueAsString(newBook);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newBookJson))
                .andExpect(status().isOk());
    }
    @Test
    void testUpdateBook() throws Exception {
        Book updatedBook = new Book(1L,"Updated Title", "Updated Author", 2020);
        String updatedBookJson = objectMapper.writeValueAsString(updatedBook);


        Mockito.doNothing().when(bookService).updateBook(Mockito.any(Book.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/book/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedBookJson))
                .andExpect(status().isOk());
    }
    @Test
    void testDeleteBook() throws Exception {
        Mockito.doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/book/1"))
                .andExpect(status().isNoContent());
    }
}