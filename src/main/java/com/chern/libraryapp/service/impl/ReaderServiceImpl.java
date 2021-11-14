package com.chern.libraryapp.service.impl;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.model.ReaderMessageInfo;
import com.chern.libraryapp.service.ReaderService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ReaderServiceImpl implements ReaderService {

    private static final Logger log = Logger.getLogger(ReaderServiceImpl.class);
    private static final ReaderServiceImpl instance = new ReaderServiceImpl();

    public static ReaderServiceImpl getInstance() {
        return instance;
    }

    private ReaderServiceImpl() {
    }

    @Override
    public List<Reader> getAllReaders() {
        try {
            return DAOFactory.readerDAO().getAllReaders();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public List<Reader> getAllReadersWhereEmailContains(String str) {
        try {
            return DAOFactory.readerDAO().getAllReadersWhereEmailContains(str);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean isEmailExists(String email) {
        try {
            return DAOFactory.readerDAO().existsByEmail(email) != null;
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void addReader(Reader reader) {
        try {
            DAOFactory.readerDAO().addReader(reader);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public Reader getReaderByEmail(String email) {
        try {
            return DAOFactory.readerDAO().existsByEmail(email);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void updateReader(Reader reader) {
        try {
            DAOFactory.readerDAO().updateReader(reader);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public List<Reader> getReadersAfter(int offset) {
        try {
            return DAOFactory.readerDAO().getReadersAfter(offset);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public List<ReaderMessageInfo> getReadersToMailDueDate() {
        try {
            return DAOFactory.readerDAO().getMailedToDueReaderInfo();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public List<ReaderMessageInfo> getReadersToMailReturnDate() {
        try {
            return DAOFactory.readerDAO().getMailedToReturnReaderInfo();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new RuntimeException();
        }
    }


}
