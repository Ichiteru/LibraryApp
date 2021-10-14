package com.chern.libraryapp.service.genre;

import com.chern.libraryapp.model.Genre;

import java.util.ArrayList;
import java.util.List;

public interface GenreService {

    public List<Genre> getAllGenres();

    public List<Genre> getBookGenresByISBN(String isbn);

    public void setNewBookGenres(String[] newGenres, List<Genre> oldGenres, String oldISBN);

}
