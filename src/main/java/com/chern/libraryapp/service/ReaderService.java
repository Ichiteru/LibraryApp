package com.chern.libraryapp.service;

import com.chern.libraryapp.model.Reader;

import java.util.List;

public interface ReaderService {

    public List<Reader> getAllReaders();
    public List<Reader> getAllReadersWhereEmailContains(String str);
}
