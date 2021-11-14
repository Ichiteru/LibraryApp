package com.chern.libraryapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error-page")
public class ExceptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("heading", "Oops!");
        req.setAttribute("message", "Something went wrong! Go to the main page and try it again!");
        getServletContext().getRequestDispatcher("/pages/error-page.jsp").forward(req,resp);
    }
}

