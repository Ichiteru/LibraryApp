package com.chern.libraryapp.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAOFactory {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/library_db";
    static final String USER = "postgres";
    static final String PASSWORD = "z12345";

    public static Connection createConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
