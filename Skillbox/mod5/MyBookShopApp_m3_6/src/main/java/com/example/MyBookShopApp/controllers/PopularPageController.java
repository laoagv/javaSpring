package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.book.book.Book;
import com.example.MyBookShopApp.book.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PopularPageController {
    private final BookService bookService;

    @Autowired
    public PopularPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recomendedBooks(){
        return bookService.getBooksData();
    }
    @GetMapping("/books/popular")
    public String popularPage(){
        return "books/popular";
    }
}
