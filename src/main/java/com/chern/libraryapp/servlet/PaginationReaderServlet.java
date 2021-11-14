package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.model.json.ReaderJSON;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/get/ten/readers")
public class PaginationReaderServlet extends HttpServlet {

    private ReaderService readerService;

    @Override
    public void init() throws ServletException {
        super.init();
        readerService = ReaderServiceImpl.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer pag = Integer.valueOf(req.getParameter("pag"));
        PrintWriter writer = resp.getWriter();
        List<Reader> readersAfter = readerService.getReadersAfter(pag);
        List<ReaderJSON> readersJSON = new ArrayList<>();
        readersAfter.forEach(reader -> readersJSON.add(new ReaderJSON(reader.getId(), reader.getEmail(), reader.getFirstName(),
                reader.getLastName(), reader.getRegistrationDate().toString(), reader.getPhone(), reader.getGender()))
        );
        String response = new Gson().toJson(readersJSON);
        resp.setContentType("application/json");
        writer.append(response);
        writer.close();
    }
}
