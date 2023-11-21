package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.comporators.AuthorComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuthorService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    public List<Author> getAuthorsData(){
        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors", (ResultSet rs, int rownum) ->{
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            author.setDescription(rs.getString("description"));
            return author;
        });
        Collections.sort(authors, new AuthorComparator());
        return new ArrayList<>(authors);
    }
}
