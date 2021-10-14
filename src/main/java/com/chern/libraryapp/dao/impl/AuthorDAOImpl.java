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
                    "WHERE book_isbn=? order by firstName";

    private static final String QUERY_SELECT_ALL_AUTHORS =
            "SELECT * from authors";

    public static final String QUERY_INSERT_NEW_AUTHOR = "INSERT INTO authors (firstName, lastName) " +
            "values (?, ?)";

    @Override
    public List<Author> getBookAuthorsByISBN(String isbn) {
        List<Author> authorList = new ArrayList<>();
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_FROM_AUTHORS_TO_CURRENT_BOOK);
            statement.setString(1, isbn);
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
