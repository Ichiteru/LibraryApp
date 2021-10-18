package com.chern.libraryapp.service.author;

import com.chern.libraryapp.model.Author;

import java.util.List;

public interface AuthorService {

    public List<Author> getAllAuthors();
    public void addSeveralAuthors(String[] authors);
    public void setNewBookAuthors(String[] newAuthors, List<Author> oldAuthors, String oldISBN);
    public List<Author> getBookAuthorsById(Long id);

}
