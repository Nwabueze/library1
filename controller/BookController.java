package com.example.library1.controller;


import com.example.library1.model.Book;
import com.example.library1.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@RequestMapping("library/book")
@RestController
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    public HttpSession session;

    // Add a new book (Login required)
    @PostMapping("/add")
    public int create(@RequestBody Book book){
        String isbn = book.getISBN();
        String title = book.getTitle();
        String author = book.getAuthor();
        String year = book.getYear();

        if(isbn.trim().isEmpty() || title.trim().isEmpty() || author.trim().isEmpty() || year.trim().isEmpty())
            return -1;
        String email = (String) session.getAttribute("email");
        if(email.isEmpty() || email == null)
            return -2;
        book.setId(UUID.randomUUID().toString());
        return bookService.create(book);
    }

    // Find book by title
    @GetMapping("/find/1/{title}")
    public List<Book> selectByTitle(@PathVariable String title){
        return bookService.selectByTitle(title);
    }

    // Find book by author and year
    @GetMapping("/find/2/{author}/{year}")
    public List<Book> selectByAuthorAndYear(@PathVariable String author, @PathVariable String year){
        return bookService.selectByAuthorAndYear(author, year);
    }

    // find all books
    @GetMapping("/all")
    public List<Book> allBooks(){
        return bookService.allBooks();
    }

    // Check if current user is currently logged in or not
    @GetMapping("/user")
    public String getUserEmail(){
        String email = (String) session.getAttribute("email");
        if(email.isEmpty() || email == null)
            return null;
        return email;
    }
}
