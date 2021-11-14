package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.model.ReaderMessageInfo;
import com.chern.libraryapp.model.json.BorrowRecordJSON;

import java.sql.SQLException;
import java.util.List;

public interface ReaderDAO {
    //interfaces of methods for concrete realization of ReaderDAO
    public List<Reader> getAllReaders() throws SQLException;
    public List<Reader> getAllReadersWhereEmailContains(String str) throws SQLException;
    public Reader findReaderById(Long id) throws SQLException;

    Reader existsByEmail(String email) throws SQLException;

    void updateFirstAndLastNameByEmail(String email, BorrowRecordJSON rec) throws SQLException;

    void addReader(BorrowRecordJSON rec) throws SQLException;

    Reader findReaderByEmail(String email) throws SQLException;

    void addReader(Reader reader) throws SQLException;

    void updateReader(Reader reader) throws SQLException;

    List<Reader> getReadersAfter(int offset) throws SQLException;

    public List<ReaderMessageInfo> getMailedToDueReaderInfo() throws SQLException;

    public List<ReaderMessageInfo> getMailedToReturnReaderInfo() throws SQLException;
}
