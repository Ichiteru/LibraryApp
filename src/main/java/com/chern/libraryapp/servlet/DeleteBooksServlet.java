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

    BookService bookService;

    @Override
    public void init() throws ServletException {
        super.init();
        bookService = BookServiceImpl.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterMap().containsKey("booksId")){
            String[] booksId = req.getParameterValues("booksId");
            bookService.deleteBooksByID(booksId);
            resp.sendRedirect("/books");
        } else {
            req.setAttribute("heading", "Oops!");
            req.setAttribute("message", "You forgot to chose a books for delete!");
            resp.sendError(409);
        }
    }
}
