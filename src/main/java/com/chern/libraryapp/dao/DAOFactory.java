package com.chern.libraryapp.dao;

import com.chern.libraryapp.dao.impl.*;

public class DAOFactory {

    public static final int POSTGRESQL = 1;

    public static ReaderDAO readerDAO() {
        return new ReaderDAOImpl();
    }

    public static BookDAO bookDAO() {
        return new BookDAOImpl();
    }

    public static AuthorDAO authorDAO() { return new AuthorDAOImpl(); }

    public static BorrowerDAO borrowerDAO() { return new BorrowerDAOImpl(); }

    public static GenreDAO genreDao() { return new GenreDAOImpl(); }


}
