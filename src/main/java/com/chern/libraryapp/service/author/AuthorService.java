package com.chern.libraryapp.service.author;

import com.chern.libraryapp.model.Author;

import java.util.List;

public interface AuthorService {

    public List<Author> getAllAuthors();
    public void addSeveralAuthors(String[] authors);

}
