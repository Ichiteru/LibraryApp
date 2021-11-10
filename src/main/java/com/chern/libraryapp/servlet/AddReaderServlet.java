package com.chern.libraryapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reader/add")
public class AddReaderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // в зависимости от того какой id делаем update или add в бд
        if (req.getParameter("id").equals("0")){
            System.out.println("add");
        } else System.out.println("update");
        resp.sendRedirect("/readers");
    }
}
