package com.chern.libraryapp.service.impl;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.service.AuthorService;
import com.chern.libraryapp.service.util.AuthorHelper;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorHelper validator;
    private static final Logger log = Logger.getLogger(AuthorServiceImpl.class);

    private static final AuthorServiceImpl instance = new AuthorServiceImpl();

    public static AuthorServiceImpl getInstance() {
        return instance;
    }

    private AuthorServiceImpl() {
        this.validator = new AuthorHelper();
    }


    @Override
    public List<Author> getAllAuthors() {
        try {
            return DAOFactory.authorDAO().getAllAuthors();
        } catch (SQLException throwables) {
            log.error("Cannot get authors from database", throwables);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Author> getBookAuthorsById(Long id) {
        try {
            return DAOFactory.authorDAO().getBookAuthorsById(id);
        } catch (SQLException throwables) {
            log.error("Cannot get authors by book id from database", throwables);
            throw new RuntimeException();
        }
    }

    @Override
    public void addNewAuthors(List<Author> authors) {
        try {
            DAOFactory.authorDAO().addNewAuthors(authors);
        } catch (SQLException throwables) {
            log.error("Cannot add new author to database", throwables);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Author> getNewAuthorsList(String[] authors) {
        return validator.convertToAuthorList(authors);
    }
}
