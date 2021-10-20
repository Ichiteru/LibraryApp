package com.chern.libraryapp.servlet;

import com.chern.libraryapp.service.book.BookService;
import com.chern.libraryapp.service.book.BookServiceImpl;

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

    BookService bookService = new BookServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] booksId = req.getParameterValues("booksId");
//        List<Long> isbnList = Arrays.stream(booksId).mapToLong(str -> Long.getLong(str)).boxed().collect(Collectors.toList());

        bookService.deleteBooksByID(booksId);
        resp.sendRedirect("/books");
        // TODO: 04.10.2021  create delete request to db
        // TODO: 04.10.2021 pagination on /books 
    }
}
