package com.chern.libraryapp.dao;

import com.chern.libraryapp.dao.impl.*;

abstract public class DAOFactory {

    public static final int POSTGRESQL = 1;

    public abstract PostgreReaderDAO readerDAO();
    public abstract PostgreBookDAO bookDAO();
    public abstract PostgreAuthorDAO authorDAO();
    public abstract PostgreBorrowerDAO borrowerDAO();
    public abstract PostgreGenreDAO genreDao();

    public static DAOFactory getDAOFactory(int factoryType) {
        switch (factoryType) {
            case POSTGRESQL: {
                return new PostgreSQLDAOFactory();
            }
            default: return null;
        }
    }
}
