package com.example.library1.repository;

import com.example.library1.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{'title': ?0}")
    public List<Book> findByTitle(String title);

    @Query("{'author': ?0, 'year': ?1 }")
    public List<Book> findByAuthorAndYear(String author, String year);
}
