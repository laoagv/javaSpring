package com.example.MyBookShopApp.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorService {

    private AuthorRepository authorRepository;


    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthorsData() {
        return authorRepository.findAll();
    }


}

//@Service
//public class AuthorService {
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public AuthorService(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}
//
//    public List<Author> getAuthorsData(){
//        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors", (ResultSet rs, int rownum) ->{
//            Author author = new Author();
//            author.setId(rs.getInt("id"));
//            author.setFirst_name(rs.getString("first_name"));
//            author.setLast_name((rs.getString("last_name")));
//            author.setDescription(rs.getString("description"));
//            return author;
//        });
//        Collections.sort(authors, new AuthorComparator());
//        return new ArrayList<>(authors);
//    }
//}
