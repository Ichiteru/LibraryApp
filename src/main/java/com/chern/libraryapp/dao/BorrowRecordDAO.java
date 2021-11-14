package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.BorrowRecord;
import com.chern.libraryapp.model.json.BorrowRecordJSON;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface BorrowRecordDAO {

    public List<BorrowRecord> getAllBookBorrowRecords(Long book_id) throws SQLException;

    void updateReturnDateAndStatus(BorrowRecordJSON rec) throws SQLException, ParseException;

    void addRecord(Long id, Long bookId, BorrowRecordJSON rec) throws SQLException, ParseException;
}
