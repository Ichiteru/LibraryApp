package com.chern.libraryapp.service.impl;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.service.ReaderService;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ReaderServiceImpl implements ReaderService {

    @Override
    public List<Reader> getAllReaders() {
        return DAOFactory.readerDAO().getAllReaders();
    }

    @Override
    public List<Reader> getAllReadersWhereEmailContains(String str) {
        return DAOFactory.readerDAO().getAllReadersWhereEmailContains(str);
    }

    @Override
    public boolean isEmailExists(String email) {
        return DAOFactory.readerDAO().existsByEmail(email) != null ? true : false;
    }

    @Override
    public void addReader(Reader reader) {
        try {
            DAOFactory.readerDAO().addReader(reader);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
//            throw new RuntimeException();
            // надо отобразить страницу с ошибкой
        }
    }

    @Override
    public Reader getReaderByEmail(String email) {
        return DAOFactory.readerDAO().existsByEmail(email);
    }

    @Override
    public void updateReader(Reader reader) {
        DAOFactory.readerDAO().updateReader(reader);
    }
}
