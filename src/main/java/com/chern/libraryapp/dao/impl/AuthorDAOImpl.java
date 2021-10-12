package com.chern.libraryapp.dao.impl;

import com.chern.libraryapp.dao.AuthorDAO;
import com.chern.libraryapp.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {

    private static final String QUERY_SELECT_FROM_AUTHORS_TO_CURRENT_BOOK =
            "SELECT id, book_isbn, firstName, lastName from authors WHERE book_isbn=?";

    @Override
    public List<Author> getBookAuthorsByISBN(Long isbn) {
        List<Author> authorList = new ArrayList<>();
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_FROM_AUTHORS_TO_CURRENT_BOOK);
            statement.setLong(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Author author = new Author();
                author.setId(resultSet.getLong(1));
                author.setBookIsbn(resultSet.getLong(2));
                author.setFirstName(resultSet.getString(3));
                author.setLastName(resultSet.getString(4));
                authorList.add(author);
            }
            return authorList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null; // FIXME: 12.10.2021 fix return statement
        }
    }
}
