package com.chern.libraryapp.dao.impl;

import com.chern.libraryapp.dao.GenreDAO;
import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAOImpl implements GenreDAO {

    public static final String QUERY_SELECT_BOOK_GENRES =
            "SELECT id, name from book_genres " +
                    "inner join genres on genres.id = book_genres.genre_id " +
                    "where book_genres.book_id=?";

    public static final String QUERY_SELECT_ALL_GENRES =
            "SELECT * FROM genres";

    public static final String QUERY_INSERT_INTO_BOOK_GENRES = "insert into book_genres (book_id, genre_id) values (?,?)";

    public static final String QUERY_DELETE_GENRES_FROM_BOOK_GENRES = "delete from book_genres where book_id=? and genre_id=?";

    public static final String QUERY_SELECT_SELECTED_GENRES = "select id from genres where name=?";

    @Override
    public List<Genre> getBookGenresById(Long id) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_BOOK_GENRES);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getLong("id"));
                genre.setName(resultSet.getString("name"));
                genres.add(genre);
            }
            return genres;
        }
    }

    @Override
    public List<Genre> getAllGenres() throws SQLException {
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_ALL_GENRES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getLong("id"));
                genre.setName(resultSet.getString("name"));
                genres.add(genre);
            }
            return genres;
        }
    }

    @Override
    public void addNewGenresToBook(List<Genre> genres, Long id) throws SQLException {
        try (Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT_INTO_BOOK_GENRES);
            connection.setAutoCommit(false);
            for (Genre g :
                    genres) {
                preparedStatement.setLong(1, id);
                preparedStatement.setLong(2, g.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        }
    }

    @Override
    public void deleteSeveralGenresFromBook(List<Genre> genres, Long id) throws SQLException {
        try (Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE_GENRES_FROM_BOOK_GENRES);
            connection.setAutoCommit(false);
            for (Genre g :
                    genres) {
                preparedStatement.setLong(1, id);
                preparedStatement.setLong(2, g.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        }
    }

    @Override
    public List<Genre> getNewGenres(List<Genre> genres) throws SQLException {
        try (Connection connection = ConnectionDAOFactory.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_SELECTED_GENRES);
            for (Genre gn : genres) {
                preparedStatement.setString(1, gn.getName());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    gn.setId(resultSet.getLong("id"));
                }
            }
            return genres;
        }
    }
}
