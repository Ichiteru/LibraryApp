package com.chern.libraryapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/delete")
public class DeleteBooksServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] booksISBN = req.getParameterValues("booksISBN");
        List<Long> isbnList = Arrays.stream(booksISBN).mapToLong(str -> Long.getLong(str)).boxed().collect(Collectors.toList());
        System.out.println(booksISBN);
        resp.sendRedirect("/books");
        // TODO: 04.10.2021  create delete request to db
        // TODO: 04.10.2021 pagination on /books 
    }
}
