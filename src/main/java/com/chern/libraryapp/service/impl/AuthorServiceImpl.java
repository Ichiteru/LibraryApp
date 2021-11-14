package com.chern.libraryapp.service.impl;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.service.AuthorService;
import com.chern.libraryapp.service.util.AuthorHelper;

import java.sql.SQLException;
import java.util.*;

public class AuthorServiceImpl implements AuthorService {

    private AuthorHelper validator;

    public AuthorServiceImpl(){
        this.validator = new AuthorHelper();
    }

    @Override
    public List<Author> getAllAuthors() {
        try {
            return DAOFactory.authorDAO().getAllAuthors();
        } catch (SQLException throwables) {
            // TODO: 14.11.2021 log
            throw new RuntimeException();
        }
    }

    @Override
    public List<Author> getBookAuthorsById(Long id) {
        try {
            return DAOFactory.authorDAO().getBookAuthorsById(id);
        } catch (SQLException throwables) {
            // TODO: 14.11.2021 log
            throw new RuntimeException();
        }
    }

    @Override
    public void addNewAuthors(List<Author> authors) {
        try {
            DAOFactory.authorDAO().addNewAuthors(authors);
        } catch (SQLException throwables) {
            // TODO: 14.11.2021 log
            throw new RuntimeException();
        }
    }

    @Override
    public List<Author> getNewAuthorsList(String[] authors) {
        return validator.convertToAuthorList(authors);
    }
}
