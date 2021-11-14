package com.chern.libraryapp.dao.impl;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDAOFactory {

//    static final String DB_URL = "jdbc:postgresql://localhost:5432/library_db";
//    static final String USER = "postgres";
//    static final String PASSWORD = "z12345";

    private static final String PROPERTIES_PATH = "/db.properties";
    private static final String URL_PROPERTY_NAME = "url";
    private static final String USER_PROPERTY_NAME = "user";
    private static final String PASSWORD_PROPERTY_NAME = "password";
    private static final Properties props = new Properties();
    private static final Logger log = Logger.getLogger(ConnectionDAOFactory.class);

    static {
        String driverName = null;
        try (InputStream inputStream = ConnectionDAOFactory.class.getResourceAsStream(PROPERTIES_PATH)) {
            props.load(inputStream);
            driverName = props.getProperty("driver");
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            log.fatal("Can't register driver: " + driverName, e);
            throw new ExceptionInInitializerError("Can't register driver: " + driverName);
        } catch (IOException e) {
            log.fatal("Can't load db config: ", e);
            throw new ExceptionInInitializerError("Can't load properties file");
        }
    }

    public static Connection createConnection() throws SQLException {
        //            Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(props.getProperty(URL_PROPERTY_NAME)
                , props.getProperty(USER_PROPERTY_NAME), props.getProperty(PASSWORD_PROPERTY_NAME));
    }

}
