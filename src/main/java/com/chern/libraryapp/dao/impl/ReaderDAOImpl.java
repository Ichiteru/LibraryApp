package com.chern.libraryapp.dao.impl;

import com.chern.libraryapp.dao.ReaderDAO;
import com.chern.libraryapp.model.Reader;
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
