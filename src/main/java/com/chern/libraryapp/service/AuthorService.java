package com.chern.libraryapp.service;

import com.chern.libraryapp.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors();
    List<Author> getBookAuthorsById(Long id);
    void addNewAuthors(List<Author> authors);
    List<Author> getNewAuthorsList(String[] authors);
}
