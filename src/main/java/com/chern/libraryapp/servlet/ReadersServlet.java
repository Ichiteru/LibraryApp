package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.service.ReaderService;
import com.chern.libraryapp.service.impl.ReaderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/readers")
public class ReadersServlet extends HttpServlet {

    private ReaderService readerService = new ReaderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Reader> readers = readerService.getAllReaders();
        req.setAttribute("readers", readers);
        req.setAttribute("readersAmount", readers.size());
        getServletContext().getRequestDispatcher("/pages/readers.jsp").forward(req,resp);
    }
}
