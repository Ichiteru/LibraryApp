package com.chern.libraryapp.service.impl;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.Genre;
import com.chern.libraryapp.model.enums.BookStatus;
import com.chern.libraryapp.service.BookService;

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
        DAOFactory.bookDAO().updateBook(book);
        List<Genre> oldBookGenres = DAOFactory.genreDao().getBookGenresById(book.getId());
        List<Genre> oldBookGenresClone = new ArrayList<>(oldBookGenres);
        List<Genre> newBookGenresClone = new ArrayList<>(newGenres);
        for (Genre newGenre: newBookGenresClone) {
            if (oldBookGenresClone.contains(newGenre)){
                oldBookGenres.remove(newGenre);
                newGenres.remove(newGenre);
            }
        }
        if (newGenres != null){
            DAOFactory.genreDao().addNewGenresToBook(newGenres, book.getId());
        }
        if (oldBookGenres != null){
            DAOFactory.genreDao().deleteSeveralGenresFromBook(oldBookGenres, book.getId());
        }

        DAOFactory.authorDAO().addNewAuthors(newAuthors); // добавление в бд новых авторов
        List<Author> newAuthorsWithId = DAOFactory.authorDAO().getNewAuthorsWithId(newAuthors);
        DAOFactory.authorDAO().getBookAuthorsById(book.getId());
        DAOFactory.authorDAO().deleteOldAuthors(book.getId());
        DAOFactory.authorDAO().addNewAuthorsToBook(newAuthorsWithId, book.getId());

    }

    @Override
    public void addNewBook(Book book) {
        if (book.getTotalAmount() > 0)
            book.setStatus(BookStatus.AVAILABLE);
        else book.setStatus(BookStatus.UNAVAILABLE);
        DAOFactory.bookDAO().addNewBook(book);
        Book bookByISBN = DAOFactory.bookDAO().findBookByISBN(book.getIsbn()); // получаем книгу с ид
        DAOFactory.authorDAO().addNewAuthors(book.getAuthors()); // заносим авторов в бд
        List<Author> newAuthorsWithId = DAOFactory.authorDAO().getNewAuthorsWithId(book.getAuthors()); // получаем новых авторов книги с ид
        DAOFactory.authorDAO().addNewAuthorsToBook(newAuthorsWithId, bookByISBN.getId()); // добавляем авторов для книги
        DAOFactory.genreDao().addNewGenresToBook(book.getGenres(), bookByISBN.getId()); // добавляем жанры для книги
    }

    @Override
    public void deleteBooksByID(String[] idList) {
        DAOFactory.bookDAO().deleteBooksByID(idList);
    }
}
