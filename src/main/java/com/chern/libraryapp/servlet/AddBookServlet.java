package com.chern.libraryapp.servlet;

import com.chern.libraryapp.dao.AuthorDAO;
import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.model.enums.BookStatus;
import com.chern.libraryapp.service.author.AuthorService;
import com.chern.libraryapp.service.author.AuthorServiceImpl;
import com.chern.libraryapp.service.book.BookService;
import com.chern.libraryapp.service.book.BookServiceImpl;
import com.chern.libraryapp.service.genre.GenreService;
import com.chern.libraryapp.service.genre.GenreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(value = "/books/add")
public class AddBookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();
    private AuthorService authorService = new AuthorServiceImpl();
    private GenreService genreService = new GenreServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Book updatedBook = new Book();
//        String isbn = req.getParameter("isbn");
//        updatedBook.setIsbn(isbn);
//        updatedBook.setTitle(req.getParameter("title"));
//        updatedBook.setDescription(req.getParameter("description"));
//        updatedBook.setPublisher(req.getParameter("publisher"));
//        try {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            Date publishDate = format.parse(req.getParameter("publishDate"));
//            updatedBook.setPublishDate(publishDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        updatedBook.setPageCount(Integer.parseInt(req.getParameter("pageCount")));
//        updatedBook.setStatus(BookStatus.valueOf(req.getParameter("status")));
//        updatedBook.setTotalAmount(Integer.parseInt(req.getParameter("totalAmount")));
//
//        if (req.getParameter("isbn").equals(isbn) || bookService.findBookByISBN(req.getParameter("isbn")) == null){
//            bookService.updateBook(updatedBook, isbn);
////            genreService.setNewBookGenres(req.getParameterValues("bookGenre"), genreService.getBookGenresByISBN(startISBN), startISBN);
////            authorService.setNewBookAuthors(req.getParameterValues("authorName"), authorService.getBookAuthorsByISBN(startISBN), startISBN);
//        }
//        resp.sendRedirect("/books/" + updatedBook.getIsbn());
    }
}

