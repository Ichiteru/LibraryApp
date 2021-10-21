package com.chern.libraryapp.service;

import com.chern.libraryapp.model.Genre;

import java.util.ArrayList;
import java.util.List;

public interface GenreService {

    public List<Genre> getAllGenres();

    public List<Genre> getBookGenresById(Long id);
    public List<Genre> getNewBookGenresList(String[] newGenres);

}
