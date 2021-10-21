package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.model.enums.TimePeriod;
import com.chern.libraryapp.service.AuthorService;
import com.chern.libraryapp.service.ReaderService;
import com.chern.libraryapp.service.impl.AuthorServiceImpl;
import com.chern.libraryapp.service.BookService;
import com.chern.libraryapp.service.impl.BookServiceImpl;
import com.chern.libraryapp.service.GenreService;
import com.chern.libraryapp.service.impl.GenreServiceImpl;
import com.chern.libraryapp.service.impl.ReaderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/books/*")
public class CurrentBookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();
    private GenreService genreService = new GenreServiceImpl();
    private AuthorService authorService = new AuthorServiceImpl();
    private ReaderService readerService = new ReaderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getPathInfo().substring(1);
        req.setAttribute("genres", genreService.getAllGenres());
        req.setAttribute("authors", authorService.getAllAuthors());
        req.setAttribute("allReaders", readerService.getAllReaders());
        System.out.println(TimePeriod.getAll());
        req.setAttribute("timePeriodArray", TimePeriod.getAll());
        Book book = new Book();
        if (bookId == "") {
            book.setPublishDate(new Date());
        } else {
            book = bookService.findBookById(Long.valueOf(bookId));
        }
        req.setAttribute("book", book);
        getServletContext().getRequestDispatcher("/WEB-INF/pages/change-book.jsp").forward(req, resp);
    }
}
