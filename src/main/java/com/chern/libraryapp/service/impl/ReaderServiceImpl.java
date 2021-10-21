package com.chern.libraryapp.service.impl;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.service.ReaderService;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {

    @Override
    public List<Reader> getAllReaders() {
        return DAOFactory.readerDAO().getAllReaders();
    }
}
