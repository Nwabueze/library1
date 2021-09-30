package com.example.library1.service;

import com.example.library1.model.Book;
import com.mongodb.client.result.UpdateResult;

public interface TemplateReaderService {

    long addReads(String email, String isbn);

    Book findOneBook(String isbn);

    long returnBook(String email, String isbn);
}
