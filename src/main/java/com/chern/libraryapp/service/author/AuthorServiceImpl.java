package com.chern.libraryapp.service.author;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.service.author.AuthorService;

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

    public void addSeveralAuthors(String[] authors){
        ArrayList<Author> list = validator.getAuthorList(authors);
        if (authors != null)
            DAOFactory.authorDAO().addSeveralAuthors(validator.getNewAuthors(list));
    }
}
