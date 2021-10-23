package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.BorrowRecord;
import com.chern.libraryapp.model.Reader;

import java.util.List;

public interface BorrowRecordDAO {

    public List<BorrowRecord> getAllBookBorrowRecords(Long book_id);
}
