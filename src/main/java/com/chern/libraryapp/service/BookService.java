package com.chern.libraryapp.service;

import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.Genre;
import com.chern.libraryapp.model.json.BorrowRecordJSON;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooks();
    public Book findBookById(Long id);
    public Book findBookByIsbn(String isbn);
    public Book findBookByTitle(String title);
    public void updateBook(Book book, List<Author> newAuthors, List<Genre> newGenres);
    public void addNewBook(Book book);

    public void deleteBooksByID(String[] idList);

    void updateBookBorrowRecords(List<BorrowRecordJSON> existRecords, List<BorrowRecordJSON> newRecords, Long bookId);
}
