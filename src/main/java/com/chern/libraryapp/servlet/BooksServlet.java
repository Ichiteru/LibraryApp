package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.service.BookService;
import com.chern.libraryapp.service.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {

    BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getPathInfo();
        String path = "";
        bookService = new BookServiceImpl();
        ServletContext servletContext = getServletContext();
        if (str==null){
            List<Book> books = bookService.getAllBooksToLoadOnTable();
            req.setAttribute("bookList", books);
            path = "/WEB-INF/pages/books.jsp";
        } else {
            Long id = Long.valueOf(str.substring(1));
            req.setAttribute("isbn", id);
            path = "/WEB-INF/pages/current-book.jsp";
        }
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
