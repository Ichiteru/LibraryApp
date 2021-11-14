package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.model.enums.Gender;
import com.chern.libraryapp.service.ReaderService;
import com.chern.libraryapp.service.impl.ReaderServiceImpl;
import org.apache.log4j.Logger;

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

    private ReaderService readerService;
    private static final Logger log = Logger.getLogger(AddReaderServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        readerService = ReaderServiceImpl.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Reader reader = createReader(req);
        if (readerService.isEmailExists(reader.getEmail()) && !req.getParameter("email").equals(req.getParameter("initEmail"))){
            req.setAttribute("heading", "Oops!");
            req.setAttribute("message", "Reader with email : " + reader.getEmail() + " already exists!");
            resp.sendError(409);
        } else{
            if (req.getParameter("id").equals("0")) readerService.addReader(reader);
            else readerService.updateReader(reader);
            resp.sendRedirect("/readers");
        }

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
            log.error(e.getMessage(), new RuntimeException());
        }
        reader.setEmail(req.getParameter("email"));
        reader.setFirstName(req.getParameter("fName"));
        reader.setLastName(req.getParameter("lName"));
        reader.setPhone(req.getParameter("phone"));
        reader.setGender(Gender.valueOf(req.getParameter("gender_radio")));
        return reader;
    }
}
