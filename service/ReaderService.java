package com.example.library1.service;

import com.example.library1.model.Book;
import com.example.library1.model.Reader;
import com.example.library1.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("readerService")
public class ReaderService extends TemplateReaderServiceImpl implements IReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public Reader create(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public List<Reader> login(String email) {

        return readerRepository.findByEmail(email);
    }

    @Override
    public List<Reader> findAll() {
        return readerRepository.findAll();
    }

    public List<Reader> findBookHolders() {
        int count = 0;
        return readerRepository.findByPicks(count);
    }


}
