package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.model.enums.Gender;
import com.chern.libraryapp.service.ReaderService;
import com.chern.libraryapp.service.impl.ReaderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/reader/add")
public class AddReaderServlet extends HttpServlet {

    private ReaderService readerService = new ReaderServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = createReader(req);
        if (readerService.isEmailExists(reader.getEmail()) && !req.getParameter("email").equals(req.getParameter("initEmail"))){
            // TODO: 12.11.2021 redirect on error page 
        } else{
            if (req.getParameter("id").equals("0"))
                    readerService.addReader(reader);
            else
                readerService.updateReader(reader);
        }

        resp.sendRedirect("/readers");
    }

    private Reader createReader(HttpServletRequest req){
        Reader reader = new Reader();
        if (!req.getParameter("id").equals("0"))
            reader.setId(Long.valueOf(req.getParameter("id")));
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date regDate = format.parse(req.getParameter("registrationDate"));
            reader.setRegistrationDate(regDate);
        } catch (ParseException e) {
            e.printStackTrace(); // TODO: 12.11.2021 redirect to error page
        }
        reader.setEmail(req.getParameter("email"));
        reader.setFirstName(req.getParameter("fName"));
        reader.setLastName(req.getParameter("lName"));
        reader.setPhone(req.getParameter("phone"));
        reader.setGender(Gender.valueOf(req.getParameter("gender_radio")));
        return reader;
    }
}
