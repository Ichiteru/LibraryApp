package com.chern.libraryapp.dao.impl;

import com.chern.libraryapp.dao.BookDAO;
import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.enums.BookStatus;
import com.chern.libraryapp.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private final String QUERY_SELECT_TO_BOOKS_TABLE =
            "SELECT * from books";
    private final String QUERY_FIND_BOOK_BY_ID =
            "SELECT * FROM books WHERE id=? limit 1";
    private final String QUERY_FIND_BOOK_BY_ISBN =
            "SELECT * FROM books WHERE isbn=? limit 1";
    private final String QUERY_FIND_BOOK_BY_TITLE =
            "SELECT * FROM books WHERE title=? limit 1";
    private final String QUERY_FIND_BOOKS_ID_BY_TITLE =
            "SELECT id FROM books WHERE title=?";
    private final String QUERY_FIND_BOOKS_ID_LIKE_DESCRIPTION =
            "SELECT id FROM books WHERE description LIKE '% (?) %'";
    private final String QUERY_UPDATE_BOOK = "UPDATE books SET " +
            "isbn=?, title=?, description=?, publisher=?, publishDate=?, pageCount=?, totalAmount=?, status=? " +
            "where id=?";
    private final String QUERY_ADD_NEW_BOOK = "insert into books (isbn, title, publisher, pagecount, totalamount, status, publishdate, description) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String QUERY_DELETE_BOOK = "delete from books where id=?";
    private final String QUERY_SELECT_TEN_BOOKS_AFTER = "select * from books offset ? rows fetch first 2 row only";
    private Book book = null;


    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_SELECT_TO_BOOKS_TABLE);
            while (resultSet.next())
                bookList.add(createBook(resultSet));
            return bookList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Book findBookById(Long id) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_FIND_BOOK_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
               book = createBook(resultSet);
            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Book findBookByISBN(String isbn) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_FIND_BOOK_BY_ISBN);
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                book = createBook(resultSet);
            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Book findBookByTitle(String title) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_FIND_BOOK_BY_TITLE);
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                book = createBook(resultSet);
            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Long> findBooksIdByTitle(String title) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            List<Long> booksId = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(QUERY_FIND_BOOKS_ID_BY_TITLE);
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                booksId.add(resultSet.getLong("id"));
            return booksId;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Long> findBooksIdWhereDescriptionLike(String description) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            List<Long> booksId = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM books WHERE description LIKE '%" + description + "%'");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                booksId.add(resultSet.getLong("id"));
            return booksId;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void updateBook(Book book) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_BOOK);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getDescription());
            statement.setString(4, book.getPublisher());
            statement.setDate(5, new Date(book.getPublishDate().getTime()));
            statement.setLong(6, book.getPageCount());
            statement.setLong(7, book.getTotalAmount());
            statement.setString(8, String.valueOf(book.getStatus()));
            statement.setLong(9, book.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addNewBook(Book book) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_ADD_NEW_BOOK);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getPublisher());
            statement.setLong(4, book.getPageCount());
            statement.setLong(5, book.getTotalAmount());
            statement.setString(6, String.valueOf(book.getStatus()));
            statement.setDate(7, new Date(book.getPublishDate().getTime()));
            statement.setString(8, book.getDescription());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteBooksByID(String[] idList) {
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE_BOOK);
            for (String id:
                 idList) {
                preparedStatement.setLong(1, Long.parseLong(id));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Book> getBooksAfter(Integer offset) {
        List<Book> bookList = new ArrayList<>();
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_TEN_BOOKS_AFTER);
            statement.setInt(1, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                bookList.add(createBook(resultSet));
            return bookList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Long> getBooksIdBy(String authorID, String query) {
        try(Connection connection = ConnectionDAOFactory.createConnection()) {
            List<Long> booksId = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, Long.parseLong(authorID));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                booksId.add(resultSet.getLong("book_id"));
            return booksId;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        }
    }


    private Book createBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setCover(resultSet.getBytes("cover"));
        book.setTitle(resultSet.getString("title"));
        book.setPublisher(resultSet.getString("publisher"));
        book.setPageCount(resultSet.getInt("pageCount"));
        book.setDescription(resultSet.getString("description"));
        book.setTotalAmount(resultSet.getInt("totalAmount"));
        book.setStatus(BookStatus.valueOf(resultSet.getString("status")));
        book.setPublishDate(resultSet.getDate("publishDate"));
        book.setAuthors(DAOFactory.authorDAO().getBookAuthorsById(book.getId()));
        book.setGenres(DAOFactory.genreDao().getBookGenresById(book.getId()));
        book.setBorrowRecords(DAOFactory.borrowRecordDAO().getAllBookBorrowRecords(book.getId()));
        return book;
    }
}
