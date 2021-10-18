package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Author;
import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.Genre;
import com.chern.libraryapp.service.author.AuthorService;
import com.chern.libraryapp.service.author.AuthorServiceImpl;
import com.chern.libraryapp.service.book.BookService;
import com.chern.libraryapp.service.book.BookServiceImpl;
import com.chern.libraryapp.service.genre.GenreService;
import com.chern.libraryapp.service.genre.GenreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/books/*")
public class CurrentBookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();
    private GenreService genreService = new GenreServiceImpl();
    private AuthorService authorService = new AuthorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getPathInfo().substring(1);
        req.setAttribute("genres", genreService.getAllGenres());
        req.setAttribute("authors", authorService.getAllAuthors());
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
