package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Genre;

import java.util.List;

public interface GenreDAO {

    public List<Genre> getBookGenresByISBN(long isbn);
}
