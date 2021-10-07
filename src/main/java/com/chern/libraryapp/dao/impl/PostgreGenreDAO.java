package com.chern.libraryapp.dao.impl;

import com.chern.libraryapp.dao.GenreDAO;
import com.chern.libraryapp.dao.PostgreSQLDAOFactory;
import com.chern.libraryapp.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgreGenreDAO implements GenreDAO {

    public static final String QUERY_SELECT_BOOK_GENRES =
            "SELECT id, name from book_genres " +
                    "inner join genres on genres.id = book_genres.genre_id " +
                    "where book_genres.book_isbn=?";
    @Override
    public List<Genre> getBookGenresByISBN(long isbn) {
        List<Genre> genres = new ArrayList<>();
        try {
            Connection connection = PostgreSQLDAOFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_BOOK_GENRES);
            preparedStatement.setLong(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Genre genre = new Genre();
                genre.setId(resultSet.getLong("id"));
                genre.setName(resultSet.getString("name"));
                genres.add(genre);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genres;
    }
}
