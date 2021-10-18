package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Genre;

import java.util.List;

public interface GenreDAO {

    public List<Genre> getBookGenresById(Long id);

    public List<Genre> getAllGenres();

    public void addNewGenresToBook(List<Genre> genres, Long id);

    public void deleteSeveralGenresFromBook(List<Genre> genres, Long id);

    public List<Genre> getSelectedGenres(String[] genres);
}
