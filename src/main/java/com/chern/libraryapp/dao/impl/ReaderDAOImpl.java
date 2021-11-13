package com.chern.libraryapp.dao.impl;

import com.chern.libraryapp.dao.ReaderDAO;
import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.model.ReaderMessageInfo;
import com.chern.libraryapp.model.enums.Gender;
import com.chern.libraryapp.model.json.BorrowRecordJSON;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAOImpl implements ReaderDAO {

    private final String QUERY_SELECT_ALL_READERS = "select * from readers";
    private final String QUERY_SELECT_ALL_READERS_LIKE = "select * from readers where email like ?";
    private final String QUERY_SELECT_READER_BY_ID = "select * from readers where id=? limit 1";
    private final String QUERY_EXIST_BY_EMAIL = "select * from readers where email=? limit 1";
    private final String QUERY_FIND_BY_EMAIL = "select * from readers where email=? limit 1";
    private final String QUERY_UPDATE_FNAME_AND_LNAME_BY_EMAIL = "update readers set firstName=?, secondName=? " +
            "where email=?";
    private final String QUERY_INSERT_INTO_READERS = "insert into readers (email, firstname, secondname, registrationdate, gender) " +
            "values (?,?,?,?,?)";

    private final String QUERY_INSERT_INTO_READERS_NEW_READER = "insert into readers (email, firstname, secondname, registrationdate, phone, gender) " +
            "values (?,?,?,?,?,?)";
    private final String QUERY_UPDATE_READER = "update readers set " +
            "email=?, firstname=?, secondname=?, registrationdate=?, phone=?, gender=? where id=?";
    private final String QUERY_SELECT_TEN_READERS_AFTER = "select * from readers offset ? rows fetch first 2 row only";

    @Override
    public List<Reader> getAllReaders() {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            List<Reader> readerList = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL_READERS);
            while (resultSet.next())
                readerList.add(createReader(resultSet));
            return readerList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reader> getAllReadersWhereEmailContains(String str) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            List<Reader> readerList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ALL_READERS_LIKE);
            statement.setString(1, "%" + str + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                readerList.add(createReader(resultSet));
            return readerList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }



    @Override
    public Reader findReaderById(Long id) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            Reader reader = null;
            PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_READER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                reader = createReader(resultSet);
            }
            return reader;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Reader existsByEmail(String email) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_EXIST_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                return createReader(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateFirstAndLastNameByEmail(String email, BorrowRecordJSON rec) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_FNAME_AND_LNAME_BY_EMAIL);
            statement.setString(1, rec.getFName());
            statement.setString(2, rec.getLName());
            statement.setString(3, email);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void addReader(BorrowRecordJSON rec) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_INTO_READERS);
            statement.setString(1, rec.getEmail());
            statement.setString(2, rec.getFName());
            statement.setString(3, rec.getLName());
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.setString(5, String.valueOf(Gender.UNDEFINED));
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Reader findReaderByEmail(String email) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_FIND_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                return createReader(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void addReader(Reader reader) throws SQLException {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_INTO_READERS_NEW_READER);
            statement.setString(1, reader.getEmail());
            statement.setString(2, reader.getFirstName());
            statement.setString(3, reader.getLastName());
            statement.setDate(4, new Date(reader.getRegistrationDate().getTime()));
            statement.setString(5, reader.getPhone());
            statement.setString(6, String.valueOf(reader.getGender()));
            statement.executeUpdate();
        }
    }

    @Override
    public void updateReader(Reader reader) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_READER);
            statement.setString(1, reader.getEmail());
            statement.setString(2, reader.getFirstName());
            statement.setString(3, reader.getLastName());
            statement.setDate(4, new Date(reader.getRegistrationDate().getTime()));
            statement.setString(5, reader.getPhone());
            statement.setString(6, String.valueOf(reader.getGender()));
            statement.setLong(7, reader.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Reader> getReadersAfter(int offset) {
        List<Reader> readers = new ArrayList<>();
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_TEN_READERS_AFTER);
            statement.setInt(1, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                readers.add(createReader(resultSet));
            return readers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ReaderMessageInfo> getMailedToDueReaderInfo(){
        List<ReaderMessageInfo> readerMessageInfo = new ArrayList<>();
        String query = "select readers.firstname, readers.secondname, readers.email, books.title, books.isbn, DATE_PART('day', due_date) - DATE_PART('day', ?::date) as daysToDue from book_readers " +
                "inner join readers on readers.id = book_readers.reader_id " +
                "inner join books on books.id = book_readers.book_id " +
                "where (DATE_PART('day', due_date) - DATE_PART('day', ?::date)) > 0 and (DATE_PART('day', due_date) - DATE_PART('day', ?::date)) <= 8 AND return_date is null";
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new Date(new java.util.Date().getTime()));
            statement.setDate(2, new Date(new java.util.Date().getTime()));
            statement.setDate(3, new Date(new java.util.Date().getTime()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                readerMessageInfo.add(createMessageInfo(resultSet));
            return readerMessageInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ReaderMessageInfo> getMailedToReturnReaderInfo(){
        List<ReaderMessageInfo> readerMessageInfo = new ArrayList<>();
        String query = "select readers.firstname, readers.secondname, readers.email, books.title, books.isbn, DATE_PART('day', ?::date) - DATE_PART('day', due_date::date) as daysToDue from book_readers \n" +
                "                inner join readers on readers.id = book_readers.reader_id \n" +
                "                inner join books on books.id = book_readers.book_id \n" +
                "                where (DATE_PART('day', ?::date) - DATE_PART('day', due_date::date)) = 1 and return_date is null";
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new Date(new java.util.Date().getTime()));
            statement.setDate(2, new Date(new java.util.Date().getTime()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                readerMessageInfo.add(createMessageInfo(resultSet));
            return readerMessageInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    private Reader createReader(ResultSet resultSet) throws SQLException {
        Reader reader = new Reader();
        reader.setId(resultSet.getLong("id"));
        reader.setEmail(resultSet.getString("email"));
        reader.setFirstName(resultSet.getString("firstname"));
        reader.setLastName(resultSet.getString("secondname"));
        reader.setRegistrationDate(resultSet.getDate("registrationDate"));
        reader.setGender(Gender.valueOf(resultSet.getString("gender")));
        reader.setPhone(resultSet.getString("phone"));
        return reader;
    }

    private ReaderMessageInfo createMessageInfo(ResultSet resultSet) throws SQLException {
        ReaderMessageInfo info = new ReaderMessageInfo();
        info.setEmail(resultSet.getString("email"));
        info.setFirstName(resultSet.getString("firstname"));
        info.setLastName(resultSet.getString("secondname"));
        info.setTitle(resultSet.getString("title"));
        info.setIsbn(resultSet.getLong("isbn"));
        info.setDaysToDue(resultSet.getInt("daysToDue"));
        return info;
    }

}
