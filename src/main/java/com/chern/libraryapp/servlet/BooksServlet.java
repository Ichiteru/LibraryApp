package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.service.BookService;
import com.chern.libraryapp.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {

    BookService bookService = new BookServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("bookAmount", bookService.getAllBooks().size());
        List<Book> books = bookService.getBooksAfter(0);
        req.setAttribute("bookList", books);
        getServletContext().getRequestDispatcher("/pages/books.jsp").forward(req, resp);
    }

}
