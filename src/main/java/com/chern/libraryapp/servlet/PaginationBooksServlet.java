package com.chern.libraryapp.servlet;

import com.chern.libraryapp.service.BookService;
import com.chern.libraryapp.service.impl.BookServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/get/ten/books")
public class PaginationBooksServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pag = Integer.valueOf(req.getParameter("pag"));
        PrintWriter writer = resp.getWriter();
        String response = new Gson().toJson(bookService.getBooksAfter(pag));
        resp.setContentType("application/json");
        writer.append(response);
        writer.close();
    }
}
