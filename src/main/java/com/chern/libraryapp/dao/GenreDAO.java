package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Genre;

import java.util.List;

public interface GenreDAO {

    public List<Genre> getBookGenresByISBN(String isbn);

    public List<Genre> getAllGenres();
}
