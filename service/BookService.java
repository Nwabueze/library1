package com.example.library1.service;

import com.example.library1.model.Book;
import com.example.library1.repository.BookRepository;
import com.mongodb.WriteConcernResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public int create(Book book) {

        Book result = bookRepository.save(book);
        if(result != null)
            return 1;
        return 0;
    }

    @Override
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> selectByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> selectByAuthorAndYear(String author, String year){
        return bookRepository.findByAuthorAndYear(author, year);
    }

}
