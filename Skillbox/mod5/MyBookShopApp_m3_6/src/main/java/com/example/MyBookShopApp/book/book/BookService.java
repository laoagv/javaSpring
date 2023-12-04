package com.example.MyBookShopApp.book.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//public class BookService {
//
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookService(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> getBooksData(){
//
//        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rownum)->{
//            Book book = new Book();
//            book.setId(rs.getInt("id"));
//            book.setAuthor(getAuthorByAuthorId(rs.getInt("author_id")));
//            book.setTitle(rs.getString("title"));
//            book.setPriceOld(rs.getString("priceOld"));
//            book.setPrice(rs.getString("price"));
//            return book;
//        });
//        return new ArrayList<>(books);
//    }
//
//    private String getAuthorByAuthorId(int authorId) {
//        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors WHERE authors.id="+authorId, (ResultSet rs, int rowNum)->{
//            Author author = new Author();
//            author.setId(rs.getInt("id"));
//            author.setFirst_name(rs.getString("first_name"));
//            author.setLast_name(rs.getString("last_name"));
//            return author;
//        });
//        return authors.get(0).toString();
//    }
//}
@Service
public class BookService {

    private BookRepository bookRepository;


    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }


}
