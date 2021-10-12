package com.chern.libraryapp.service;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Book;

import java.util.List;

public class BookServiceImpl implements BookService{

    private DAOFactory factory = new DAOFactory();

    @Override
    public List<Book> getAllBooksToLoadOnTable() {
        List<Book> books = factory.bookDAO().getAllToBooksTable();
        return books;
    }

    @Override
    public Book findBookByISBN(Long isbn) {
        Book book = factory.bookDAO().findBookByISBN(isbn);
        return book;
    }
}
