package com.example.library1.repository;

import com.example.library1.model.Reader;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderRepository extends MongoRepository<Reader, String> {
    @Query("{'email': ?0}")
    public List<Reader> findByEmail(String email);

    @Query("{'picks': { $gt: ?0 }}")
    public List<Reader> findByPicks(int picks);
}
