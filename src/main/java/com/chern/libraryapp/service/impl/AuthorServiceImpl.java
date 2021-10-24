package com.chern.libraryapp.service.impl;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.service.AuthorService;
import com.chern.libraryapp.service.validator.AuthorValidator;

import java.util.*;

public class AuthorServiceImpl implements AuthorService {

    private AuthorValidator validator;

    public AuthorServiceImpl(){
        this.validator = new AuthorValidator();
    }

    @Override
    public List<Author> getAllAuthors() {
        return DAOFactory.authorDAO().getAllAuthors();
    }

    @Override
    public List<Author> getBookAuthorsById(Long id) {
        return DAOFactory.authorDAO().getBookAuthorsById(id);
    }

    @Override
    public void addNewAuthors(List<Author> authors) {
        DAOFactory.authorDAO().addNewAuthors(authors);
    }

    @Override
    public List<Author> getNewAuthorsList(String[] authors) {
        return validator.convertToAuthorList(authors);
    }
}