package com.example.MyBookShopApp.comporators;

import com.example.MyBookShopApp.data.Author;

import java.util.Comparator;

public class AuthorComparator implements Comparator<Author> {
    public int compare(Author author1, Author author2){
        return author1.getName().compareTo(author2.getName());
    }

}
