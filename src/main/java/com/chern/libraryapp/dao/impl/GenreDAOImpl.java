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
                    "where book_genres.book_isbn=?";

    public static final String QUERY_SELECT_ALL_GENRES =
            "SELECT * FROM genres";

    public static final String QUERY_INSERT_INTO_BOOK_GENRES = "insert into book_genres (book_isbn, genre_id) values (?,?)";

    public static final String QUERY_DELETE_GENRES_FROM_BOOK_GENRES = "delete from book_genres where book_isbn=? and genre_id=?";

    public static final String QUERY_SELECT_SELECTED_GENRES = "select id from genres where name=?";

    @Override
    public List<Genre> getBookGenresByISBN(String isbn) {
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_BOOK_GENRES);
            preparedStatement.setString(1, isbn);
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

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_ALL_GENRES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Genre genre = new Genre();
                genre.setId(resultSet.getLong("id"));
                genre.setName(resultSet.getString("name"));
                genres.add(genre);
            }
            return genres;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public void addSeveralGenresToBook(List<Genre> genres, String isbn) {
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT_INTO_BOOK_GENRES);
            connection.setAutoCommit(false);
            for (Genre g :
                    genres) {
                preparedStatement.setString(1, isbn);
                preparedStatement.setLong(2, g.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteSeveralGenresFromBook(List<Genre> genres, String isbn) {
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE_GENRES_FROM_BOOK_GENRES);
            connection.setAutoCommit(false);
            for (Genre g :
                    genres) {
                preparedStatement.setString(1, isbn);
                preparedStatement.setLong(2, g.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Genre> getSelectedGenres(String[] genres) {
        List<Genre> genreList = new ArrayList<>();
        try (Connection connection = ConnectionDAOFactory.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_SELECTED_GENRES);
            for (String gn :
                    genres) {
                preparedStatement.setString(1, gn);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Genre genre = new Genre();
                    genre.setId(resultSet.getLong("id"));
                    genre.setName(gn);
                    genreList.add(genre);
                }
            }
            return genreList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
