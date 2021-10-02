package com.chern.libraryapp.service;

import com.chern.libraryapp.model.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooksToLoadOnTable();
}
