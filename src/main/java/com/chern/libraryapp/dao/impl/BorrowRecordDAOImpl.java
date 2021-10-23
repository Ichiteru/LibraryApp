package com.chern.libraryapp.dao.impl;

import com.chern.libraryapp.dao.BorrowRecordDAO;
import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.BorrowRecord;
import com.chern.libraryapp.model.enums.BookStatus;
import com.chern.libraryapp.model.enums.ReturnedBookStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowRecordDAOImpl implements BorrowRecordDAO {

    private final String QUERY_SELECT_ALL_BOOK_BORROW_RECORDS = "select * from book_readers where book_id =?";

    @Override
    public List<BorrowRecord> getAllBookBorrowRecords(Long book_id) {
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            List<BorrowRecord> borrowRecords = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_ALL_BOOK_BORROW_RECORDS);
            preparedStatement.setLong(1, book_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                borrowRecords.add(createBorrowRecord(resultSet));
            return borrowRecords;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    private BorrowRecord createBorrowRecord(ResultSet resultSet) throws SQLException {
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBookId(resultSet.getLong("book_id"));
        borrowRecord.setReaderId(resultSet.getLong("reader_id"));
        borrowRecord.setBorrowDate(resultSet.getDate("borrow_date"));
        borrowRecord.setDueDate(resultSet.getDate("due_date"));
        borrowRecord.setReturnDate(resultSet.getDate("return_date"));
        borrowRecord.setTimePeriod(resultSet.getInt("timePeriod"));
        if (resultSet.getString("status") != null)
            borrowRecord.setStatus(ReturnedBookStatus.valueOf(resultSet.getString("status")));
        borrowRecord.setComment(resultSet.getString("comment"));
        borrowRecord.setReader(DAOFactory.readerDAO().findReaderById(borrowRecord.getReaderId()));
        return borrowRecord;
    }
}
