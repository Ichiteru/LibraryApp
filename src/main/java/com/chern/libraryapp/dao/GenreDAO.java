package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Genre;

import java.util.List;

public interface GenreDAO {

    public List<Genre> getBookGenresByISBN(String isbn);

    public List<Genre> getAllGenres();

    public void addSeveralGenresToBook(List<Genre> genres, String isbn);

    public void deleteSeveralGenresFromBook(List<Genre> genres, String isbn);

    public List<Genre> getSelectedGenres(String[] genres);
}
