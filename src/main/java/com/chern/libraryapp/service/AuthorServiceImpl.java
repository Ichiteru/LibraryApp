package com.chern.libraryapp.service;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Author;

import java.util.List;

public class AuthorServiceImpl implements AuthorService{


    @Override
    public List<Author> getAllAuthors() {
        return DAOFactory.authorDAO().getAllAuthors();
    }
}
