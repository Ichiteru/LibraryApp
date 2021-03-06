package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.Genre;
import com.chern.libraryapp.model.enums.BookStatus;
import com.chern.libraryapp.model.json.BorrowRecordJSON;
import com.chern.libraryapp.service.AuthorService;
import com.chern.libraryapp.service.impl.AuthorServiceImpl;
import com.chern.libraryapp.service.BookService;
import com.chern.libraryapp.service.impl.BookServiceImpl;
import com.chern.libraryapp.service.GenreService;
import com.chern.libraryapp.service.impl.GenreServiceImpl;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet("/books/change")
public class ChangeBookServlet extends HttpServlet {

    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;
    private static final Logger log = Logger.getLogger(ChangeBookServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        bookService = BookServiceImpl.getInstance();
        authorService = AuthorServiceImpl.getInstance();
        genreService = GenreServiceImpl.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String oldIsbn = req.getParameter("startIsbn");
        String newIsbn = req.getParameter("isbn");
        List<Genre> newBookGenres = genreService.getNewBookGenresList(req.getParameterValues("bookGenre"));
        List<Author> newBookAuthors = authorService.getNewAuthorsList(req.getParameterValues("authorName"));
        List<BorrowRecordJSON> existRecords = new ArrayList<>();
        List<BorrowRecordJSON> newRecords = new ArrayList<>();
        if (req.getParameterMap().containsKey("existingRecords")){
            existRecords = Arrays.asList(new Gson().fromJson(req.getParameter("existingRecords"), BorrowRecordJSON[].class));
        }
        if (req.getParameterMap().containsKey("newRecords")){
            newRecords = Arrays.asList(new Gson().fromJson(req.getParameter("newRecords"), BorrowRecordJSON[].class));
        }
        Book updatedBook = createBookWithNewParams(req);
        if (oldIsbn.equals(newIsbn) || bookService.findBookByIsbn(newIsbn) == null) {
            bookService.updateBook(updatedBook, newBookAuthors, newBookGenres);
            bookService.updateBookBorrowRecords(existRecords, newRecords, updatedBook.getId());
            resp.sendRedirect("/books/" + updatedBook.getId());
        } else {
            req.setAttribute("heading", "Oops!");
            req.setAttribute("message", "Book with ISBN : " + newIsbn + " already exists!");
            resp.sendError(409);
        }
    }

    private Book createBookWithNewParams(HttpServletRequest req) {
        Book updatedBook = new Book();
        updatedBook.setId(Long.valueOf(req.getParameter("bookId")));
        updatedBook.setCover(req.getParameter("cover"));
        updatedBook.setIsbn(req.getParameter("isbn"));
        updatedBook.setTitle(req.getParameter("title"));
        updatedBook.setDescription(req.getParameter("description"));
        updatedBook.setPublisher(req.getParameter("publisher"));
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date publishDate = format.parse(req.getParameter("publishDate"));
            updatedBook.setPublishDate(publishDate);
        } catch (ParseException e) {
            log.error(e.getMessage(), new RuntimeException());
        }
        updatedBook.setPageCount(Integer.parseInt(req.getParameter("pageCount")));
        updatedBook.setStatus(BookStatus.valueOf(req.getParameter("status")));
        updatedBook.setTotalAmount(Integer.parseInt(req.getParameter("totalAmount")));
        return updatedBook;
    }
}
