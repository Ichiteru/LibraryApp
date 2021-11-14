package com.chern.libraryapp.servlet;

import com.chern.libraryapp.service.ReaderService;
import com.chern.libraryapp.service.impl.ReaderServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/get/readers/by-email")
public class LoadReaderByEmailServlet extends HttpServlet {

    ReaderService readerService;

    @Override
    public void init() throws ServletException {
        super.init();
        readerService = ReaderServiceImpl.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        PrintWriter writer = resp.getWriter();
        String responseString = new Gson().toJson(readerService.getAllReadersWhereEmailContains(email));
        resp.setContentType("application/json");
        writer.append(responseString);
        writer.close();
    }
}
