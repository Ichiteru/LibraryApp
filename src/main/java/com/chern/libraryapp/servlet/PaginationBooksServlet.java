package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.json.BookJSON;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/get/ten/books")
public class PaginationBooksServlet extends HttpServlet {

    private BookService bookService;

    @Override
    public void init() throws ServletException {
        super.init();
        bookService = BookServiceImpl.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer pag = Integer.valueOf(req.getParameter("pag"));
        PrintWriter writer = resp.getWriter();
        List<Book> booksAfter = bookService.getBooksAfter(pag);
        List<BookJSON> booksJSON = new ArrayList<>();
        booksAfter.forEach(book -> booksJSON.add(new BookJSON(book.getId(), book.getTitle(), book.getPublishDate().toString(),
                    book.getTotalAmount(), book.getAuthors()))
        );
        String response = new Gson().toJson(booksJSON);
        resp.setContentType("application/json");
        writer.append(response);
        writer.close();
    }
}
