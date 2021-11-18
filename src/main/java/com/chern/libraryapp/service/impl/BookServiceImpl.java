package com.chern.libraryapp.service.impl;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.Genre;
import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.model.enums.BookStatus;
import com.chern.libraryapp.model.json.BorrowRecordJSON;
import com.chern.libraryapp.service.BookService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookServiceImpl implements BookService {

    private List<Book> searchedBooks;
    private final static Logger log = Logger.getLogger(BookServiceImpl.class);
    private final String QUERY_FIND_BOOKS_ID_BY_AUTHOR_ID =
            "SELECT book_id FROM book_authors WHERE author_id=?";
    private final String QUERY_FIND_BOOKS_ID_BY_GENRE_ID =
            "SELECT book_id FROM book_genres WHERE genre_id=?";

    private static final BookServiceImpl instance = new BookServiceImpl();

    public static BookServiceImpl getInstance() {
        return instance;
    }


    private BookServiceImpl() {
        searchedBooks = new ArrayList<>();
    }

    public List<Book> getSearchedBooks() {
        return searchedBooks;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books;
        try {
            books = DAOFactory.bookDAO().getAllBooks();
        } catch (SQLException throwables) {
            log.error("Cannot get books from database", throwables);
            throw new RuntimeException();
        }
        return books;
    }

    public Book findBookById(Long id) {
        Book book;
        try {
            book = DAOFactory.bookDAO().findBookById(id);
        } catch (SQLException throwables) {
            log.error("Cannot find book by ID in database", throwables);
            throw new RuntimeException();
        }
        return book;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        try {
            return DAOFactory.bookDAO().findBookByISBN(isbn);
        } catch (SQLException throwables) {
            log.error("Cannot find book by ISBN in database", throwables);
            throw new RuntimeException();
        }
    }

    @Override
    public Book findBookByTitle(String title) {
        try {
            return DAOFactory.bookDAO().findBookByTitle(title);
        } catch (SQLException throwables) {
            log.error("Cannot find book by title in database", throwables);
            throw new RuntimeException();
        }
    }

    @Override
    public void updateBook(Book book, List<Author> newAuthors, List<Genre> newGenres) {
        try {
            DAOFactory.bookDAO().updateBook(book);
            List<Genre> oldBookGenres = DAOFactory.genreDao().getBookGenresById(book.getId());
            List<Genre> oldBookGenresClone = new ArrayList<>(oldBookGenres);
            List<Genre> newBookGenresClone = new ArrayList<>(newGenres);
            for (Genre newGenre : newBookGenresClone) {
                if (oldBookGenresClone.contains(newGenre)) {
                    oldBookGenres.remove(newGenre);
                    newGenres.remove(newGenre);
                }
            }
            if (!newGenres.isEmpty()) {
                DAOFactory.genreDao().addNewGenresToBook(newGenres, book.getId());
            }
            if (!oldBookGenres.isEmpty()) {
                DAOFactory.genreDao().deleteSeveralGenresFromBook(oldBookGenres, book.getId());
            }

            DAOFactory.authorDAO().addNewAuthors(newAuthors); // добавление в бд новых авторов
            List<Author> newAuthorsWithId = DAOFactory.authorDAO().getNewAuthorsWithId(newAuthors);
            DAOFactory.authorDAO().getBookAuthorsById(book.getId());
            DAOFactory.authorDAO().deleteOldAuthors(book.getId());
            DAOFactory.authorDAO().addNewAuthorsToBook(newAuthorsWithId, book.getId());
        } catch (SQLException throwables) {
            log.error("Cannot update book", throwables);
            throw new RuntimeException();
        }

    }

    @Override
    public void addNewBook(Book book) {
        if (book.getTotalAmount() > 0)
            book.setStatus(BookStatus.AVAILABLE);
        else book.setStatus(BookStatus.UNAVAILABLE);
        try {
            DAOFactory.bookDAO().addNewBook(book);
        } catch (SQLException throwables) {
            log.error("Cannot add new book to database", throwables);
            throw new RuntimeException();
        }
        Book bookByISBN; // получаем книгу с ид
        try {
            bookByISBN = DAOFactory.bookDAO().findBookByISBN(book.getIsbn());
            DAOFactory.authorDAO().addNewAuthors(book.getAuthors()); // заносим авторов в бд
            List<Author> newAuthorsWithId = DAOFactory.authorDAO().getNewAuthorsWithId(book.getAuthors()); // получаем новых авторов книги с ид
            DAOFactory.authorDAO().addNewAuthorsToBook(newAuthorsWithId, bookByISBN.getId()); // добавляем авторов для книги
            DAOFactory.genreDao().addNewGenresToBook(book.getGenres(), bookByISBN.getId()); // добавляем жанры для книги
        } catch (SQLException throwables) {
            log.error("Cannot add authors/genres to new book", throwables);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Book> getBooksAfter(Integer offset) {
        try {
            return DAOFactory.bookDAO().getBooksAfter(offset);
        } catch (SQLException throwables) {
            log.error("Cannot get next number of books from database", throwables);
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteBooksByID(String[] idList) {
        try {
            DAOFactory.bookDAO().deleteBooksByID(idList);
        } catch (SQLException throwables) {
            log.error("Cannot delete books by ID's from database", throwables);
            throw new RuntimeException();
        }
    }

    @Override
    public void updateBookBorrowRecords(List<BorrowRecordJSON> existRecords, List<BorrowRecordJSON> newRecords, Long bookId) {
        try {
            for (BorrowRecordJSON rec :
                    existRecords) {
                if (!rec.getReturnDate().equals("")) {
                    DAOFactory.borrowRecordDAO().updateReturnDateAndStatus(rec);
                }
            }
            for (BorrowRecordJSON rec :
                    newRecords) {
                Reader reader = DAOFactory.readerDAO().existsByEmail(rec.getEmail());
                if (reader != null) {
                    DAOFactory.borrowRecordDAO().addRecord(reader.getId(), bookId, rec);
                    DAOFactory.readerDAO().updateFirstAndLastNameByEmail(reader.getEmail(), rec);
                } else {
                    DAOFactory.readerDAO().addReader(rec);
                    Reader readerByEmail = DAOFactory.readerDAO().findReaderByEmail(rec.getEmail());
                    DAOFactory.borrowRecordDAO().addRecord(readerByEmail.getId(), bookId, rec);
                }
            }
        } catch (SQLException | ParseException throwables) {
            log.error("Cannot update book borrow records", throwables);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Book> getSearchBooks(Map<String, String[]> parameterMap) {
        List<Long> allBooksID = new ArrayList<>();
        try {
            DAOFactory.bookDAO().getAllBooks().forEach(book -> allBooksID.add(book.getId()));
        } catch (SQLException throwables) {
            log.error("Cannot get all books from database", throwables);
            throw new RuntimeException();
        }
        final List<Long>[] booksIdByTitle = new List[]{new ArrayList<>()};
        final List<Long>[] booksIdByAuthors = new List[]{new ArrayList<>()};
        final List<Long>[] booksIdByGenres = new List[]{new ArrayList<>()};
        final List<Long>[] booksIdByDescription = new List[]{new ArrayList<>()};
        parameterMap.forEach((k, v) -> {
            if (k.equals("title") && !parameterMap.get(k)[0].equals("")) {
                try {
                    booksIdByTitle[0] = DAOFactory.bookDAO().findBooksIdByTitle(parameterMap.get(k)[0]);
                } catch (SQLException throwables) {
                    log.error("Cannot find book by title in search operation", throwables);
                    throw new RuntimeException();
                }
            } else if (k.equals("authorId")) {
                booksIdByAuthors[0] = getRetainedBooksID(parameterMap.get(k), QUERY_FIND_BOOKS_ID_BY_AUTHOR_ID);
            } else if (k.equals("genreId")) {
                booksIdByGenres[0] = getRetainedBooksID(parameterMap.get(k), QUERY_FIND_BOOKS_ID_BY_GENRE_ID);
            } else if (k.equals("description") && !parameterMap.get(k)[0].equals("")) {
                try {
                    booksIdByDescription[0] = DAOFactory.bookDAO().findBooksIdWhereDescriptionLike(parameterMap.get(k)[0]);
                } catch (SQLException throwables) {
                    log.error("Cannot get book by description in search operation", throwables);
                    throw new RuntimeException();
                }
            }
        });
        if (!booksIdByTitle[0].isEmpty())
            allBooksID.retainAll(booksIdByTitle[0]);
        if (!booksIdByAuthors[0].isEmpty())
            allBooksID.retainAll(booksIdByAuthors[0]);
        if (!booksIdByGenres[0].isEmpty())
            allBooksID.retainAll(booksIdByGenres[0]);
        if (!booksIdByDescription[0].isEmpty())
            allBooksID.retainAll(booksIdByDescription[0]);
        if (booksIdByTitle[0].isEmpty() && booksIdByAuthors[0].isEmpty() && booksIdByGenres[0].isEmpty() && booksIdByDescription[0].isEmpty())
            allBooksID.clear();
        searchedBooks = getSearchedBooksID(allBooksID);
        System.out.println(searchedBooks);
        return searchedBooks;
    }

    private List<Long> getRetainedBooksID(String[] authorsId, String query) {
        try {
            if (authorsId.length == 1)
                return DAOFactory.bookDAO().getBooksIdBy(authorsId[0], query);
            else {
                List<Long> booksId;
                booksId = DAOFactory.bookDAO().getBooksIdBy(authorsId[0], query);
                for (int i = 1; i < authorsId.length; i++) {
                    if (booksId.isEmpty())
                        return booksId;
                    booksId.retainAll(DAOFactory.bookDAO().getBooksIdBy(authorsId[i], query));
                }
                return booksId;
            }
        } catch (SQLException throwables) {
            log.error("Cannot get retained books from database", throwables);
            throw new RuntimeException();
        }
    }

    private List<Book> getSearchedBooksID(List<Long> idArray) {
        List<Book> searchedBooksList = new ArrayList<>();
        if (idArray.isEmpty()) return null;
        else {
            for (Long aLong : idArray) {
                try {
                    searchedBooksList.add(DAOFactory.bookDAO().findBookById(aLong));
                } catch (SQLException throwables) {
                    log.error("Cannot get book by ID from database", throwables);
                    throw new RuntimeException();
                }
            }
            return searchedBooksList;
        }
    }
}


