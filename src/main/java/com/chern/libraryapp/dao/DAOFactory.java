package com.chern.libraryapp.dao;

import com.chern.libraryapp.dao.impl.PostgreAuthorDAO;
import com.chern.libraryapp.dao.impl.PostgreBookDAO;
import com.chern.libraryapp.dao.impl.PostgreBorrowerDAO;
import com.chern.libraryapp.dao.impl.PostgreReaderDAO;

abstract public class DAOFactory {

    public static final int POSTGRESQL = 1;

    public abstract PostgreReaderDAO readerDAO();
    public abstract PostgreBookDAO bookDAO();
    public abstract PostgreAuthorDAO authorDAO();
    public abstract PostgreBorrowerDAO borrowerDAO();

    public static DAOFactory getDAOFactory(int factoryType) {
        switch (factoryType) {
            case POSTGRESQL: {
                return new PostgreSQLDAOFactory();
            }
            default: return null;
        }
    }
}
