package com.chern.libraryapp.dao.impl;

import com.chern.libraryapp.dao.BookDAO;
import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.enums.BookStatus;
import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private static final String QUERY_SELECT_TO_BOOKS_TABLE =
            "SELECT isbn, title, publishdate, totalamount from books";
    public static final String QUERY_FIND_BOOK_BY_ID =
            "SELECT * FROM books WHERE isbn=?";


    @Override
    public List<Book> getAllToBooksTable() {
        List<Book> bookList = new ArrayList<>();
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_SELECT_TO_BOOKS_TABLE);
            while (resultSet.next()){
                Book book = new Book();
                book.setIsbn(resultSet.getString(1));
                book.setTitle(resultSet.getString(2));
                book.setPublishDate(resultSet.getDate(3));
                book.setTotalAmount(resultSet.getInt(4));
                List<Author> bookAuthorsByISBN = DAOFactory.authorDAO().getBookAuthorsByISBN(book.getIsbn());
                book.setAuthors(bookAuthorsByISBN);
                bookList.add(book);
            }
            return bookList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Book findBookByISBN(String isbn) {
        Book book = null;
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_FIND_BOOK_BY_ID);
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                book = new Book();
                book.setIsbn(resultSet.getString("isbn"));
                book.setCover(resultSet.getBytes("cover"));
                book.setTitle(resultSet.getString("title"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setPageCount(resultSet.getInt("pageCount"));
                book.setDescription(resultSet.getString("description"));
                book.setTotalAmount(resultSet.getInt("totalAmount"));
                book.setStatus(BookStatus.valueOf(resultSet.getString("status")));
                book.setPublishDate(resultSet.getDate("publishDate"));
                book.setAuthors(DAOFactory.authorDAO().getBookAuthorsByISBN(isbn));
                book.setGenres(DAOFactory.genreDao().getBookGenresByISBN(isbn));
            }
            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
