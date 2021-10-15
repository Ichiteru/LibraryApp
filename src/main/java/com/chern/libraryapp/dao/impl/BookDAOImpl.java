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
            "SELECT * FROM books WHERE isbn=? limit 1";
    public static final String QUERY_FIND_BOOK_BY_TITLE =
            "SELECT * FROM books WHERE title=? limit 1";
    public static final String QUERY_UPDATE_BOOK = "UPDATE books SET " +
            "isbn=?, title=?, description=?, publisher=?, publishDate=?, pageCount=?, totalAmount=?, status=? " +
            "where isbn=?";


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

    @Override
    public Book findBookByTitle(String title) {
        Book book = null;
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_FIND_BOOK_BY_TITLE);
            statement.setString(1, title);
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
                book.setAuthors(DAOFactory.authorDAO().getBookAuthorsByISBN(resultSet.getString("isbn")));
                book.setGenres(DAOFactory.genreDao().getBookGenresByISBN(resultSet.getString("isbn")));
            }
            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateBook(Book book, String oldISBN) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_BOOK);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getDescription());
            statement.setString(4, book.getPublisher());
            java.util.Date publishDate = book.getPublishDate();
            Date sqlDate = new Date(publishDate.getYear(), publishDate.getMonth(), publishDate.getDay());
            statement.setDate(5, sqlDate);
            statement.setLong(6, book.getPageCount());
            statement.setLong(7, book.getTotalAmount());
            statement.setString(8, String.valueOf(book.getStatus()));
            statement.setString(9, oldISBN);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }
}
