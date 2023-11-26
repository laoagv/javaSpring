package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.AuthorService;
import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AuthorPageContoller {
    private final AuthorService authorService;

    public AuthorPageContoller(AuthorService authorService) {
        this.authorService = authorService;
    }
    @ModelAttribute("authors")
    public List<Author> authors(){
        return authorService.getAuthorsData();
    }
    @ModelAttribute("authorsAndChars")
    public Map<String,List<Author>> authorsAndChars(){
        Map<String,List<Author>> dictionary = new HashMap<String, List<Author>>();
        List<Author> authors = authorService.getAuthorsData();
        String firstChar;

        for (Author author:authors) {
            firstChar = Character.toString(author.getName().charAt(0));
            if (dictionary.containsKey(firstChar)){
                dictionary.get(firstChar).add(author);
            }
            else {
                List<Author> res = new ArrayList<Author>();
                res.add(author);
                dictionary.put(firstChar,  res);
            }
        }
        return dictionary;
    }
    @GetMapping("/authors")
    public String authorPage(){
//        model.addAttribute("bookData", bookService.getBooksData());
        return "authors/index";
    }
}
