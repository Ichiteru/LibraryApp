package com.chern.libraryapp.service.book;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = DAOFactory.bookDAO().getAllBooks();
        return books;
    }

    public Book findBookById(Long id) {
        Book book = DAOFactory.bookDAO().findBookById(id);
        return book;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return DAOFactory.bookDAO().findBookByISBN(isbn);
    }

    @Override
    public Book findBookByTitle(String title) {
        return DAOFactory.bookDAO().findBookByTitle(title);
    }

    @Override
    public void updateBook(Book book, List<Author> newAuthors, List<Genre> newGenres) {
//        DAOFactory.bookDAO().updateBook(book);
        List<Genre> oldBookGenres = DAOFactory.genreDao().getBookGenresById(book.getId());
        List<Genre> oldBookGenresClone = new ArrayList<>(oldBookGenres);
        List<Genre> newBookGenresClone = new ArrayList<>(newGenres);
        for (Genre newGenre: newBookGenresClone) {
            if (oldBookGenresClone.contains(newGenre)){
                oldBookGenres.remove(newGenre);
                newGenres.remove(newGenre);
            }
        }
        // new insert old delete
        DAOFactory.genreDao().addNewGenresToBook(newGenres, book.getId());
        DAOFactory.genreDao().deleteSeveralGenresFromBook(oldBookGenres, book.getId());
//        List<Author> oldBookAuthors = DAOFactory.authorDAO().getBookAuthorsById(book.getId());
    }
}
