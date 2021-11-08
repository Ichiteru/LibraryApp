package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.BorrowRecord;
import com.chern.libraryapp.model.json.BorrowRecordJSON;

import java.util.List;

public interface BorrowRecordDAO {

    public List<BorrowRecord> getAllBookBorrowRecords(Long book_id);

    void updateReturnDateAndStatus(BorrowRecordJSON rec);

    void addRecord(Long id, Long bookId, BorrowRecordJSON rec);
}
