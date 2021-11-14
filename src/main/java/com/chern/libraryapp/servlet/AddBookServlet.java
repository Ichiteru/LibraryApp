package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.Genre;
import com.chern.libraryapp.service.AuthorService;
import com.chern.libraryapp.service.BookService;
import com.chern.libraryapp.service.GenreService;
import com.chern.libraryapp.service.impl.AuthorServiceImpl;
import com.chern.libraryapp.service.impl.BookServiceImpl;
import com.chern.libraryapp.service.impl.GenreServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/books/add")
public class AddBookServlet extends HttpServlet {

    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;
    private static final Logger log = Logger.getLogger(AddBookServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        bookService = BookServiceImpl.getInstance();
        authorService = AuthorServiceImpl.getInstance();
        genreService = GenreServiceImpl.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // проверка на isbn
        String isbn = req.getParameter("isbn");
        if (bookService.findBookByIsbn(isbn) == null){
            Book newBook = createBook(req);
            List<Genre> newBookGenres = genreService.getNewBookGenresList(req.getParameterValues("bookGenre"));
            List<Author> newBookAuthors = authorService.getNewAuthorsList(req.getParameterValues("authorName"));
            newBook.setAuthors(newBookAuthors);
            newBook.setGenres(newBookGenres);
            bookService.addNewBook(newBook);
        } else {
            req.setAttribute("heading", "Oops!");
            req.setAttribute("message", "Book with ISBN : " + isbn + " already exists!");
            resp.sendError(409);
        }
        resp.sendRedirect("/books");
    }

    private Book createBook(HttpServletRequest req) {
        Book newBook = new Book();
        newBook.setIsbn(req.getParameter("isbn"));
        newBook.setTitle(req.getParameter("title"));
        newBook.setDescription(req.getParameter("description"));
        newBook.setPublisher(req.getParameter("publisher"));
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date publishDate = format.parse(req.getParameter("publishDate"));
            newBook.setPublishDate(publishDate);
        } catch (ParseException e) {
            log.error(e.getMessage(), new RuntimeException());
        }
        newBook.setPageCount(Integer.parseInt(req.getParameter("pageCount")));
        newBook.setTotalAmount(Integer.parseInt(req.getParameter("totalAmount")));
        return newBook;
    }
}

