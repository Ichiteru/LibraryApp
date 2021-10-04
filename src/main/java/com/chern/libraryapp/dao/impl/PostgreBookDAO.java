package com.chern.libraryapp.dao.impl;

import com.chern.libraryapp.dao.BookDAO;
import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.dao.PostgreSQLDAOFactory;
import com.chern.libraryapp.enums.BookStatus;
import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostgreBookDAO implements BookDAO {
    //realization CRUD and other queries for book

    private static final String QUERY_SELECT_TO_BOOKS_TABLE =
            "SELECT isbn, title, publishdate, totalamount from books";
    public static final String QUERY_FIND_BOOK_BY_ID =
            "SELECT * FROM books WHERE isbn=?";


    @Override
    public List<Book> getAllToBooksTable() {
        List<Book> bookList = new ArrayList<>();
        try {
            Connection connection = PostgreSQLDAOFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_SELECT_TO_BOOKS_TABLE);
            while (resultSet.next()){
                Book book = new Book();
                book.setIsbn(resultSet.getLong(1));
                book.setTitle(resultSet.getString(2));
                book.setPublishDate(resultSet.getDate(3));
                book.setTotalAmount(resultSet.getInt(4));
                List<Author> bookAuthorsByISBN = PostgreSQLDAOFactory
                        .getDAOFactory(1).authorDAO().getBookAuthorsByISBN(book.getIsbn());
                book.setAuthors(bookAuthorsByISBN);
                bookList.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return bookList;
        }
    }

    @Override
    public Book findBookByISBN(Long isbn) {
        Book book = null;
        try {
            Connection connection = PostgreSQLDAOFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY_FIND_BOOK_BY_ID);
            statement.setLong(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                book = new Book();
                book.setIsbn(resultSet.getLong("isbn"));
                book.setCover(resultSet.getBytes("cover"));
                book.setTitle(resultSet.getString("title"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setPageCount(resultSet.getInt("pageCount"));
                book.setDescription(resultSet.getString("description"));
                book.setTotalAmount(resultSet.getInt("totalAmount"));
                book.setStatus(BookStatus.valueOf(resultSet.getString("status")));
                book.setPublishDate(resultSet.getDate("publishDate"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return book;
        }
    }
}
