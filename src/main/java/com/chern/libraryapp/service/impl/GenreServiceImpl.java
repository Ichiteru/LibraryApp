package com.chern.libraryapp.service.impl;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Genre;
import com.chern.libraryapp.service.GenreService;
import com.chern.libraryapp.service.util.GenreHelper;

import java.sql.SQLException;
import java.util.List;

public class GenreServiceImpl implements GenreService {

    private GenreHelper validator;

    public GenreServiceImpl() {
        this.validator = new GenreHelper();
    }

    @Override
    public List<Genre> getAllGenres(){
        try {
            return DAOFactory.genreDao().getAllGenres();
        } catch (SQLException throwables) {
            // TODO: 14.11.2021 log
            throw new RuntimeException();
        }
    }

    @Override
    public List<Genre> getBookGenresById(Long id) {
        try {
            return DAOFactory.genreDao().getBookGenresById(id);
        } catch (SQLException throwables) {
            // TODO: 14.11.2021 log
            throw new RuntimeException();
        }
    }

    public List<Genre> getNewBookGenresList(String[] newGenres){
        try {
            return DAOFactory.genreDao().getNewGenres(validator.transformToList(newGenres));
        } catch (SQLException throwables) {
            // TODO: 14.11.2021 log
            throw new RuntimeException();
        }
    }
}
