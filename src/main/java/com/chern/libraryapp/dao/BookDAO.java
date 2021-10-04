package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Book;

import java.util.List;

public interface BookDAO {
    //interfaces of methods for concrete realization of BookDAO

    public List<Book> getAllToBooksTable();
    public Book findBookByISBN(Long isbn);
}
