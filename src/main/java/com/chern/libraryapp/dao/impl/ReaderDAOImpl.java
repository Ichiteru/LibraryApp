package com.chern.libraryapp.dao.impl;

import com.chern.libraryapp.dao.ReaderDAO;
import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.model.enums.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAOImpl implements ReaderDAO {

    public static final String QUERY_SELECT_ALL_READERS = "select * from readers";
    public static final String QUERY_SELECT_ALL_READERS_LIKE = "select * from readers where email like ?";
    public static final String QUERY_SELECT_READER_BY_ID = "select * from readers where id=? limit 1";

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
}
