package com.example.library1.service;

import com.example.library1.model.Reader;

import java.util.List;


public interface IReaderService {

    Reader create(Reader reader);

    List<Reader> login(String email);

    List<Reader> findAll();

    List<Reader> findBookHolders();

}
