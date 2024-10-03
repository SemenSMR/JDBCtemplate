package com.example.jdbc.repository;

import com.example.jdbc.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Book> bookRowMapper = (rs, rowNum) -> {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setPublicationYear(rs.getInt("publication_year"));
        return book;
    };

    public List<Book> findAll(){
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql,bookRowMapper);
    }
    public Book findById(Long id){
        String sql = "SELECT * FROM book WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,bookRowMapper);
    }

    public int save(Book book){
        String sql = "INSERT INTO book (title,author,publication_year) VALUES(?,?,?)";
        return jdbcTemplate.update(sql,book.getTitle(),book.getAuthor(),book.getPublicationYear());

    }
    public int update(Book book){
        String sql = "UPDATE book SET title = ?, author = ?, publication_year = ? WHERE id = ?";
        return jdbcTemplate.update(sql,book.getTitle(),book.getAuthor(),book.getPublicationYear(),book.getId());
    }
    public int delete(Long id){
        String sql = "DELETE FROM book WHERE id = ?";
        return jdbcTemplate.update(sql,id);
    }

}
