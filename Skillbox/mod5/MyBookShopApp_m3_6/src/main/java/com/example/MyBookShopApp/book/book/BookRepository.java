package com.example.MyBookShopApp.book.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

//    List<Book> findBooksByAuthor_firstName(String name);

    @Query("from Book")
    List<Book> customFindAllBooks();
}