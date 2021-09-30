package com.example.library1.service;


import com.example.library1.model.Book;
import com.example.library1.model.Reader;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.internal.connection.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

//import static com.mongodb.client.model.Filters.where;

public class TemplateReaderServiceImpl implements TemplateReaderService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public long addReads(String email, String isbn){

        // Find the given book
        Query query1 = new Query();
        Book book = mongoTemplate.findOne(query1.addCriteria(Criteria.where("isbn").is(isbn)), Book.class);
        if(book != null){
            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));
            Update update = new Update();

            update.addToSet("books", book).inc("count", 1);
            UpdateResult result = mongoTemplate.updateFirst(query, update, Reader.class);
            return result.getModifiedCount();
        }

        return 0;
    }

    @Override
    public Book findOneBook(String isbn) {

        Query query = new Query();
        return mongoTemplate.findOne(query.addCriteria(Criteria.where("isbn").is(isbn)), Book.class);
    }

    @Override
    public long returnBook(String email, String isbn) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        Update update = new Update();

        // Remove the book from the users book collection
        update.pull("books", new BasicDBObject("isbn", isbn)).inc("count", -1);
        UpdateResult result = mongoTemplate.updateFirst(query, update, Reader.class);
        return result.getModifiedCount();

    }

    public List<Reader> activeBookHolders(){
        Query query = new Query();
        return mongoTemplate.find(query.addCriteria(Criteria.where("count").gt(0)), Reader.class);
    }


}
