package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Author;

import java.util.List;

public interface AuthorDAO {

    public List<Author> getBookAuthorsByISBN(Long isbn);
}
