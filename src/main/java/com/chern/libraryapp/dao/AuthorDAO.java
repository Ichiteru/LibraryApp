package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Genre;

import java.sql.SQLException;
import java.util.List;

public interface AuthorDAO {

    public List<Author> getBookAuthorsById(Long id) throws SQLException;

    public List<Author> getAllAuthors() throws SQLException;

    public void addSeveralAuthors(List<Author> authors) throws SQLException;

    public void addNewAuthors(List<Author> authors) throws SQLException;

    public List<Author> getNewAuthorsWithId(List<Author> authors) throws SQLException;

    public void addNewAuthorsToBook(List<Author> authors, Long bookId) throws SQLException;

    public void deleteOldAuthors(Long bookId) throws SQLException;
}
