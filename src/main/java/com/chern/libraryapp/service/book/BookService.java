package com.chern.libraryapp.service.book;

import com.chern.libraryapp.model.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooks();
    public Book findBookByISBN(String isbn);
    public Book findBookByTitle(String title);
    public void updateBook(Book book, String oldISBN);
}
