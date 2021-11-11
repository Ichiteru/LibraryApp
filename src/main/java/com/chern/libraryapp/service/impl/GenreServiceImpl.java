package com.chern.libraryapp.service.impl;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Genre;
import com.chern.libraryapp.service.GenreService;
import com.chern.libraryapp.service.validator.GenreConverter;

import java.util.List;

public class GenreServiceImpl implements GenreService {

    private GenreConverter validator;

    public GenreServiceImpl() {
        this.validator = new GenreConverter();
    }

    @Override
    public List<Genre> getAllGenres(){
        return DAOFactory.genreDao().getAllGenres();
    }

    @Override
    public List<Genre> getBookGenresById(Long id) {
        return DAOFactory.genreDao().getBookGenresById(id);
    }

    public List<Genre> getNewBookGenresList(String[] newGenres){
        return DAOFactory.genreDao().getNewGenres(validator.transformToList(newGenres));
    }
}
