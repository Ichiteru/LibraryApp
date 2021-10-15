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
import java.util.List;

@WebServlet("/books/*")
public class CurrentBookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();
    private GenreService genreService = new GenreServiceImpl();
    private AuthorService authorService = new AuthorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getPathInfo();
        String id = str.substring(1);
        Book book = bookService.findBookByISBN(id);
        req.setAttribute("book", book);
        List<Genre> allGenres = genreService.getAllGenres();
        req.setAttribute("genres", allGenres);
        List<Author> allAuthors = authorService.getAllAuthors();
        req.setAttribute("authors", allAuthors);
        getServletContext().getRequestDispatcher("/WEB-INF/pages/change-book.jsp").forward(req, resp);
    }
}
