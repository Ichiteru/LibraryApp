package com.chern.libraryapp.service;

import com.chern.libraryapp.model.Reader;

import java.util.List;

public interface ReaderService {

    public List<Reader> getAllReaders();
    public List<Reader> getAllReadersWhereEmailContains(String str);

    boolean isEmailExists(String email);

    void addReader(Reader reader);

    Reader getReaderByEmail(String email);

    void updateReader(Reader reader);
}
