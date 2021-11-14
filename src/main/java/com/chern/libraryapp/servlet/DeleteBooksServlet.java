package com.chern.libraryapp.servlet;

import com.chern.libraryapp.service.BookService;
import com.chern.libraryapp.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteBooksServlet extends HttpServlet {

    BookService bookService = new BookServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("booksId")){
            String[] booksId = req.getParameterValues("booksId");
            bookService.deleteBooksByID(booksId);
            resp.sendRedirect("/books");
        } else {
            // TODO: 14.11.2021 error page
        }
    }
}
