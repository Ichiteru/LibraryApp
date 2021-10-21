package com.chern.libraryapp.service;

import com.chern.libraryapp.model.Author;

import java.util.List;

public interface AuthorService {

    public List<Author> getAllAuthors();
    public List<Author> getBookAuthorsById(Long id);
    public void addNewAuthors(List<Author> authors);
    public List<Author> getNewAuthorsList(String[] authors);
}
