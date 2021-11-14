package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {
    //interfaces of methods for concrete realization of BookDAO

    public List<Book> getAllBooks() throws SQLException;
    public Book findBookById(Long id) throws SQLException;
    public Book findBookByISBN(String isbn) throws SQLException;
    public Book findBookByTitle(String title) throws SQLException;

    List<Long> findBooksIdByTitle(String title) throws SQLException;

    List<Long> findBooksIdWhereDescriptionLike(String description) throws SQLException;

    public void updateBook(Book book) throws SQLException;
    public void addNewBook(Book book) throws SQLException;
    public void deleteBooksByID(String[] idList) throws SQLException;
    public List<Book> getBooksAfter(Integer offset) throws SQLException;

    List<Long> getBooksIdBy(String authorID, String query) throws SQLException;
}
