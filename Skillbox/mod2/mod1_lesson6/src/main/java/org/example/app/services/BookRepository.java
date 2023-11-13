package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
//    private final List<Book> repo = new ArrayList<>();
    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    public BookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> retreiveAll() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum)->{
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });
        return new ArrayList<>(books);
    }

    @Override
    public void store(Book book) {
//        book.setId(book.hashCode());
        logger.info("store new book: " + book);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", book.getAuthor());
        parameterSource.addValue("title", book.getTitle());
        parameterSource.addValue("size", book.getSize());

        jdbcTemplate.update("INSERT INTO books(author, title, size) VALUES(:author, :title, :size)", parameterSource);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", bookIdToRemove);
        jdbcTemplate.update("DELETE FROM books WHERE id = :id", parameterSource);
        logger.info("remove book completed: ");
        return true;

    }

    @Override
    public boolean removeItemRegex(String bookRegexToRemove) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", bookRegexToRemove);
        parameterSource.addValue("title", bookRegexToRemove);
        try {
            parameterSource.addValue("size", Integer.parseInt(bookRegexToRemove));
        }catch (Exception e){
            jdbcTemplate.update("DELETE FROM books WHERE author = :author OR title = :title", parameterSource);
            logger.info("remove book completed: ");
            return true;
        } ;

        jdbcTemplate.update("DELETE FROM books WHERE author = :author OR title = :title OR size =:size", parameterSource);
        logger.info("remove book completed: ");
        return true;
    }
}
