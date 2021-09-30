package com.example.library1.controller;


import com.example.library1.model.Reader;
import com.example.library1.service.IReaderService;
import com.example.library1.service.TemplateReaderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RequestMapping("library/reader")
@RestController
public class ReaderController {

    @Autowired
    private IReaderService readerService;

    @Autowired
    private TemplateReaderServiceImpl templateReaderService;

    @Autowired
    public HttpSession session;

    @PostMapping("/register")
    public int register(@RequestBody Reader reader){

        String firstname = reader.getFirstname();
        String lastname = reader.getLastname();
        String email = reader.getEmail();
        String password = reader.getPassword();

        if(firstname.trim().isEmpty() || lastname.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty())
            // input contains empty values
            return -1;

        List<Reader> user = readerService.login(email);
        if(user.size() > 0){
            // Email address is already in use
            return 0;
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        reader.setId(UUID.randomUUID().toString());
        reader.setAdmin();
        reader.setCount();
        reader.setBooks();
        reader.setPassword(encodedPassword);
        Reader bookReader =  readerService.create(reader);

        if(bookReader != null){
            session.setAttribute("email", email);
        }
        // Account was successfully created;
        return 1;
    }

    @GetMapping("/all")
    public List<Reader> getAllReaders(){
        return readerService.findAll();
    }

    @PostMapping("/login/{email}/{passw}")
    public int userLogin(@PathVariable String email, @PathVariable String passw){
        if(email.isEmpty() || passw.isEmpty())
            return -1;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<Reader> reader = readerService.login(email);
        if(reader.size() > 0){
            String password = reader.get(0).getPassword();
            boolean correctPassword = passwordEncoder.matches(passw, password);
            if(correctPassword){
                session.setAttribute("email", email);
                return 1;
            }
        }
       return 0;
    }

    @PutMapping("/add/{isbn}")
    public long borrowBookFromLibrary(@PathVariable String isbn){

        /*
          Although books are borrowed by selecting their ISBN (a unique number),
          for simplicity, we show the reader/user all the information about the book
          such as author, title, year and ISBN
        */
        String email = (String) session.getAttribute("email");

        // Must be logged in before you can borrow a book
        if(email.isEmpty() || email == null)
            return 0;
        return templateReaderService.addReads(email, isbn);
    }

    @GetMapping("/borrowed")
    public List<Reader> getAllActiveBookHolders(){
        String email = (String) session.getAttribute("email");
        if(email.isEmpty() || email == null){
            // Just return an empty list
            return new ArrayList<>();
        }
        return templateReaderService.activeBookHolders();
    }

    @DeleteMapping("/user/{email}")
    public int addEmail(@PathVariable String email){
        String email1 = (String) session.getAttribute("email");
        if(email1.isEmpty())
            return -1;
        return 1;
    }

    // Return a book you took from library
    @DeleteMapping("/borrowed/{isbn}")
    public long returnBorrowedBook(@PathVariable String isbn){
        String email = (String) session.getAttribute("email");
        if(email.isEmpty())
            return -1;
        return templateReaderService.returnBook(email, isbn);
    }
}
