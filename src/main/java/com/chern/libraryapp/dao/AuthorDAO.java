package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Genre;

import java.util.List;

public interface AuthorDAO {

    public List<Author> getBookAuthorsByISBN(String isbn);

    public List<Author> getAllAuthors();

    public void addSeveralAuthors(List<Author> authors);

    public List<Author> getSelectedAuthors(List<Author> authors);

    public void addSeveralAuthorsToBook(List<Author> authors, String isbn);

    public void deleteSeveralAuthorsFromBook(List<Author> authors, String isbn);
}
