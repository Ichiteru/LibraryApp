package com.chern.libraryapp.service.book;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.service.book.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {



    @Override
    public List<Book> getAllBooks() {
        List<Book> books = DAOFactory.bookDAO().getAllToBooksTable();
        return books;
    }

    @Override
    public Book findBookByISBN(String isbn) {
        Book book = DAOFactory.bookDAO().findBookByISBN(isbn);
        return book;
    }

    @Override
    public Book findBookByTitle(String title) {
        return DAOFactory.bookDAO().findBookByTitle(title);
    }

    @Override
    public void updateBook(Book book, String oldISBN) {
        DAOFactory.bookDAO().updateBook(book, oldISBN);
    }
}
