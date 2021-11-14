package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Book;
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
import java.util.List;
import java.util.Map;

@WebServlet("/search/books")
public class SearchBookServlet extends HttpServlet {

    private AuthorService authorService;
    private GenreService genreService;
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        super.init();
        bookService = BookServiceImpl.getInstance();
        authorService = AuthorServiceImpl.getInstance();
        genreService = GenreServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> searchedBooks = bookService.getSearchedBooks();
        req.setAttribute("bookList", searchedBooks);
        req.setAttribute("authors", authorService.getAllAuthors());
        req.setAttribute("genres", genreService.getAllGenres());
        getServletContext().getRequestDispatcher("/pages/search-page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        bookService.getSearchBooks(parameterMap);
        resp.sendRedirect("/search/books");
    }
}
