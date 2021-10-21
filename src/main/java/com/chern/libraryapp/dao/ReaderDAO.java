package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Reader;

import java.util.List;

public interface ReaderDAO {
    //interfaces of methods for concrete realization of ReaderDAO
    public List<Reader> getAllReaders();
    public List<Reader> getAllReadersWhereEmailContains(String str);
}
