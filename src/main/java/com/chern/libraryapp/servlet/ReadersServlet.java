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

    private ReaderService readerService;

    @Override
    public void init() throws ServletException {
        super.init();
        readerService = ReaderServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Reader> allReaders = readerService.getAllReaders();
        req.setAttribute("readersAmount", allReaders.size());
        List<Reader> readers = readerService.getReadersAfter(0);
        req.setAttribute("readers", readers);
        getServletContext().getRequestDispatcher("/pages/readers.jsp").forward(req,resp);
    }
}
