package com.chern.libraryapp.servlet;

import com.chern.libraryapp.service.author.AuthorService;
import com.chern.libraryapp.service.author.AuthorServiceImpl;
import com.chern.libraryapp.service.BookService;
import com.chern.libraryapp.service.BookServiceImpl;
import com.chern.libraryapp.service.genre.GenreService;
import com.chern.libraryapp.service.genre.GenreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/books/change")
public class ChangeBookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();
    private AuthorService authorService = new AuthorServiceImpl();
    private GenreService genreService = new GenreServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isbn = req.getParameter("isbn");
        String startISBN = req.getParameter("startIsbn");
        req.getParameter("startIsbn");
        req.getParameter("title");
        req.getParameter("description");
        req.getParameter("publisher");
        req.getParameter("publishDate");
        req.getParameter("pageCount");
        req.getParameter("totalAmount");
        req.getParameter("status");
        String[] authorNames = req.getParameterValues("authorName");
        String[] bookGenres = req.getParameterValues("bookGenre");
        genreService.setNewBookGenres(bookGenres, genreService.getBookGenresByISBN(startISBN), startISBN);
        authorService.setNewBookAuthors(authorNames, authorService.getBookAuthorsByISBN(startISBN), startISBN);

    }
}
