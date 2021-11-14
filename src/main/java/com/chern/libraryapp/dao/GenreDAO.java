package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreDAO {

    public List<Genre> getBookGenresById(Long id) throws SQLException;

    public List<Genre> getAllGenres() throws SQLException;

    public void addNewGenresToBook(List<Genre> genres, Long id) throws SQLException;

    public void deleteSeveralGenresFromBook(List<Genre> genres, Long id) throws SQLException;

    public List<Genre> getNewGenres(List<Genre> genres) throws SQLException;
}
