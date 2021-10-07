package com.chern.libraryapp.dao;

import com.chern.libraryapp.dao.impl.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLDAOFactory extends DAOFactory {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/library_db";
    static final String USER = "postgres";
    static final String PASSWORD = "z12345";

    public static Connection createConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }
    }

    @Override
    public PostgreReaderDAO readerDAO() {
        return new PostgreReaderDAO();
    }

    @Override
    public PostgreBookDAO bookDAO() {
        return new PostgreBookDAO();
    }

    @Override
    public PostgreAuthorDAO authorDAO() { return new PostgreAuthorDAO(); }

    @Override
    public PostgreBorrowerDAO borrowerDAO() { return new PostgreBorrowerDAO(); }

    @Override
    public PostgreGenreDAO genreDao() {
        return new PostgreGenreDAO();
    }
}
