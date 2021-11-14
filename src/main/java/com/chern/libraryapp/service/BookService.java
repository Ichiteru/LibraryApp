package com.chern.libraryapp.service;

import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.Genre;
import com.chern.libraryapp.model.json.BorrowRecordJSON;

import java.util.List;
import java.util.Map;

public interface BookService {

    List<Book> getAllBooks();
    Book findBookById(Long id);
    Book findBookByIsbn(String isbn);
    Book findBookByTitle(String title);
    void updateBook(Book book, List<Author> newAuthors, List<Genre> newGenres);
    void addNewBook(Book book);
    List<Book> getBooksAfter(Integer offset);

    void deleteBooksByID(String[] idList);

    void updateBookBorrowRecords(List<BorrowRecordJSON> existRecords, List<BorrowRecordJSON> newRecords, Long bookId);

    List<Book> getSearchBooks(Map<String, String[]> parameterMap);

    List<Book> getSearchedBooks();
}
