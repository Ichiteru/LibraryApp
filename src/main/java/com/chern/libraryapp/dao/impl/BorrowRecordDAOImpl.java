package com.chern.libraryapp.dao.impl;

import com.chern.libraryapp.dao.BorrowRecordDAO;
import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.BorrowRecord;
import com.chern.libraryapp.model.enums.ReturnedBookStatus;
import com.chern.libraryapp.model.json.BorrowRecordJSON;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BorrowRecordDAOImpl implements BorrowRecordDAO {

    private final String QUERY_SELECT_ALL_BOOK_BORROW_RECORDS = "select * from book_readers where book_id =?";

    private final String QUERY_UPDATE_RETURN_DATE_AND_STATUS_BY_ID = "update book_readers set " +
            "return_date=?, status=? where id=?";

    private final String QUERY_INSERT_INTO_BOOK_READERS = "insert into book_readers values (?,?,?,?,?,?,?,?)";
    private final String QUERY_INSERT_INTO_BOOK_READERS_WITHOUT_RETURN_DATE = "insert into book_readers " +
            "(book_id, reader_id, borrow_date, due_date, comment, timeperiod) values (?,?,?,?,?,?)";

    @Override
    public List<BorrowRecord> getAllBookBorrowRecords(Long book_id) throws SQLException {
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            List<BorrowRecord> borrowRecords = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_ALL_BOOK_BORROW_RECORDS);
            preparedStatement.setLong(1, book_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                borrowRecords.add(createBorrowRecord(resultSet));
            return borrowRecords;
        }
    }

    @Override
    public void updateReturnDateAndStatus(BorrowRecordJSON rec) throws SQLException, ParseException {
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_RETURN_DATE_AND_STATUS_BY_ID);
            statement.setDate(1, new Date(new SimpleDateFormat("yyyy-MM-dd").parse(rec.getReturnDate()).getTime()));
            statement.setString(2, rec.getReturnStatus());
            statement.setLong(3, rec.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void addRecord(Long id, Long bookId, BorrowRecordJSON rec) throws SQLException, ParseException {
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement statement;
            if (rec.getReturnDate().equals("")){
                statement = connection.prepareStatement(QUERY_INSERT_INTO_BOOK_READERS_WITHOUT_RETURN_DATE);
                statement.setLong(1, bookId);
                statement.setLong(2, id);
                statement.setDate(3, new Date(new SimpleDateFormat("yyyy-MM-dd").parse(rec.getBorrowDate()).getTime()));
                statement.setDate(4, new Date(new SimpleDateFormat("yyyy-MM-dd").parse(rec.getDueDate()).getTime()));
                statement.setString(5, rec.getComment());
                statement.setInt(6, rec.getTimePeriod());
            } else {
                statement = connection.prepareStatement(QUERY_INSERT_INTO_BOOK_READERS);
                statement.setLong(1, bookId);
                statement.setLong(2, id);
                statement.setDate(3, new Date(new SimpleDateFormat("yyyy-MM-dd").parse(rec.getBorrowDate()).getTime()));
                statement.setDate(4, new Date(new SimpleDateFormat("yyyy-MM-dd").parse(rec.getDueDate()).getTime()));
                statement.setDate(5, new Date(new SimpleDateFormat("yyyy-MM-dd").parse(rec.getReturnDate()).getTime()));
                if (rec.getComment().equals(""))
                    statement.setString(6, "none");
                else statement.setString(6, rec.getComment());
                statement.setInt(7, rec.getTimePeriod());
                statement.setString(8, rec.getReturnStatus());
            }
            statement.executeUpdate();
        }
    }

    private BorrowRecord createBorrowRecord(ResultSet resultSet) throws SQLException {
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setId(resultSet.getLong("id"));
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
