package com.chern.libraryapp.service;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Genre;

import java.util.List;

public class GenreServiceImpl implements GenreService{

    @Override
    public List<Genre> getAllGenres(){
        return DAOFactory.genreDao().getAllGenres();
    }
}
