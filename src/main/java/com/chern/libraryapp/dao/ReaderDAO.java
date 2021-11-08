package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.model.json.BorrowRecordJSON;

import java.util.List;

public interface ReaderDAO {
    //interfaces of methods for concrete realization of ReaderDAO
    public List<Reader> getAllReaders();
    public List<Reader> getAllReadersWhereEmailContains(String str);
    public Reader findReaderById(Long id);

    Reader existsByEmail(String email);

    void updateFirstAndLastNameByEmail(String email, BorrowRecordJSON rec);

    void addReader(BorrowRecordJSON rec);

    Reader findReaderByEmail(String email);
}
