package com.chern.libraryapp.dao;

import com.chern.libraryapp.model.Book;

import java.util.List;

public interface BookDAO {
    //interfaces of methods for concrete realization of BookDAO

    public List<Book> getAllBooks();
    public Book findBookById(Long id);
    public Book findBookByISBN(String isbn);
    public Book findBookByTitle(String title);
    public void updateBook(Book book);
    public void addNewBook(Book book);
    public void deleteBooksByID(String[] idList);
    public List<Book> getBooksAfter(Integer offset);
}
