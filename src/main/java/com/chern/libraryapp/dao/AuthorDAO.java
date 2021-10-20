package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Genre;

import java.util.List;

public interface AuthorDAO {

    public List<Author> getBookAuthorsById(Long id);

    public List<Author> getAllAuthors();

    public void addSeveralAuthors(List<Author> authors);

    public void addNewAuthors(List<Author> authors);

    public List<Author> getNewAuthorsWithId(List<Author> authors);

    public void addNewAuthorsToBook(List<Author> authors, Long bookId);

    public void deleteOldAuthors(Long bookId);
}
