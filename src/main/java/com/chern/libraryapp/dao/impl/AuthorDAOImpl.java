package com.chern.libraryapp.dao.impl;

import com.chern.libraryapp.dao.AuthorDAO;
import com.chern.libraryapp.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {

    private static final String QUERY_SELECT_FROM_AUTHORS_TO_CURRENT_BOOK =
            "SELECT id, firstName, lastName from book_authors " +
                    " inner join authors on authors.id = book_authors.author_id " +
                    "WHERE book_id=? order by firstName";

    private static final String QUERY_SELECT_ALL_AUTHORS =
            "SELECT * from authors";

    public static final String QUERY_INSERT_NEW_AUTHOR = "INSERT INTO authors (firstName, lastName) " +
            "values (?, ?)";

    public static final String QUERY_SELECT_AUTHORS_BY_FNAME_AND_LNAME = "select * from authors where " +
            "firstname=? and lastname=?";

    public static final String QUERY_INSERT_NEW_AUTHORS_TO_BOOK = "insert into book_authors (book_id, author_id) values (?,?)";

    public static final String QUERY_INSERT_IF_EXISTS = "insert into authors (firstname, lastname) " +
            "select ?, ? " +
            "where not exists (select * from authors where firstname=? and lastname=?)";

    public static final String QUERY_DELETE_OLD_AUTHORS = "delete from book_authors where book_id=?";

    @Override
    public List<Author> getBookAuthorsById(Long id) {
        List<Author> authorList = new ArrayList<>();
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_FROM_AUTHORS_TO_CURRENT_BOOK);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return createAuthor(authorList, resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null; // FIXME: 12.10.2021 fix return statement
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ALL_AUTHORS);
            ResultSet resultSet = statement.executeQuery();
            return createAuthor(authorList, resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null; // FIXME: 12.10.2021 fix return statement
        }
    }

    @Override
    public void addSeveralAuthors(List<Author> authors) {
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT_NEW_AUTHOR);
            for (Author a :
                authors) {
                preparedStatement.setString(1, a.getFirstName());
                preparedStatement.setString(2, a.getLastName());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    @Override
    public void addNewAuthors(List<Author> authors) {
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT_IF_EXISTS);
            connection.setAutoCommit(false);
            for (Author a :
                    authors) {
                preparedStatement.setString(1, a.getFirstName());
                preparedStatement.setString(2, a.getLastName());
                preparedStatement.setString(3, a.getFirstName());
                preparedStatement.setString(4, a.getLastName());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Author> getNewAuthorsWithId(List<Author> authors) {
            List<Author> authorList = new ArrayList<>();
            try (Connection connection = ConnectionDAOFactory.createConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_AUTHORS_BY_FNAME_AND_LNAME);
                for (Author a :
                        authors) {
                    preparedStatement.setString(1, a.getFirstName());
                    preparedStatement.setString(2, a.getLastName());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        Author author = new Author();
                        author.setId(resultSet.getLong("id"));
                        author.setFirstName(resultSet.getString("firstname"));
                        author.setLastName(resultSet.getString("lastname"));
                        authorList.add(author);
                    }
                }
                return authorList;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
    }

    @Override
    public void addNewAuthorsToBook(List<Author> authors, Long bookId) {
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT_NEW_AUTHORS_TO_BOOK);
            connection.setAutoCommit(false);
            for (Author a :
                    authors) {
                preparedStatement.setLong(1, bookId);
                preparedStatement.setLong(2, a.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteOldAuthors(Long bookId) {
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE_OLD_AUTHORS);
            preparedStatement.setLong(1, bookId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private List<Author> createAuthor(List<Author> authorList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            Author author = new Author();
            author.setId(resultSet.getLong("id"));
            author.setFirstName(resultSet.getString("firstName"));
            author.setLastName(resultSet.getString("lastName"));
            authorList.add(author);
        }
        return authorList;
    }
}
