package com.chern.libraryapp.servlet;

import com.chern.libraryapp.service.BookService;
import com.chern.libraryapp.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/change/*")
public class ChangeBookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isbn = req.getParameter("isbn");
        req.setAttribute("book", bookService.findBookByISBN(isbn));
        getServletContext().getRequestDispatcher("/WEB-INF/pages/change-book.jsp").forward(req, resp);
    }
}
