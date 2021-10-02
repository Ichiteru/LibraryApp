package com.chern.libraryapp.service;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.dao.PostgreSQLDAOFactory;
import com.chern.libraryapp.model.Book;

import java.util.List;

public class BookServiceImpl implements BookService{

    private DAOFactory factory;

    @Override
    public List<Book> getAllBooksToLoadOnTable() {
        factory = DAOFactory.getDAOFactory(1);
        List<Book> books = factory.bookDAO().getAllToBooksTable();
        return books;
    }
}
