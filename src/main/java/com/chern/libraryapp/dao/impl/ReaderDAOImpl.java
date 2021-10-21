package com.chern.libraryapp.dao.impl;

import com.chern.libraryapp.dao.ReaderDAO;
import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.model.enums.Gender;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAOImpl implements ReaderDAO {

    public static final String QUERY_SELECT_ALL_READERS = "select * from readers";

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
