package com.example.library1.service;

import com.example.library1.model.Book;

import java.util.List;

public interface IBookService {

    int create(Book book);

    List<Book> allBooks();

    public List<Book> selectByTitle(String title);

    List<Book> selectByAuthorAndYear(String author, String year);
    
}
