package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.enums.ReturnedBookStatus;
import com.chern.libraryapp.model.enums.TimePeriod;
import com.chern.libraryapp.service.AuthorService;
import com.chern.libraryapp.service.BookService;
import com.chern.libraryapp.service.GenreService;
import com.chern.libraryapp.service.impl.AuthorServiceImpl;
import com.chern.libraryapp.service.impl.BookServiceImpl;
import com.chern.libraryapp.service.impl.GenreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@WebServlet("/books/*")
public class CurrentBookServlet extends HttpServlet {

    private BookService bookService;
    private GenreService genreService;
    private AuthorService authorService;

    @Override
    public void init() throws ServletException {
        super.init();
        bookService = BookServiceImpl.getInstance();
        authorService = AuthorServiceImpl.getInstance();
        genreService = GenreServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getPathInfo().substring(1);
        req.setAttribute("genres", genreService.getAllGenres());
        req.setAttribute("authors", authorService.getAllAuthors());
        req.setAttribute("timePeriodArray", TimePeriod.getAll());
        req.setAttribute("returnedBookStatus", Arrays.stream(ReturnedBookStatus.values()).toArray());
        Book book = new Book();
        if (bookId.equals("")) { //
            book.setPublishDate(new Date());
        } else {
            book = bookService.findBookById(Long.valueOf(bookId));
        }
        req.setAttribute("book", book);
        getServletContext().getRequestDispatcher("/pages/change-book.jsp").forward(req, resp);
    }
}
