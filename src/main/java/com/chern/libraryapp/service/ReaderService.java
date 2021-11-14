package com.chern.libraryapp.service;

import com.chern.libraryapp.model.Reader;
import com.chern.libraryapp.model.ReaderMessageInfo;

import java.util.List;

public interface ReaderService {

     List<Reader> getAllReaders();
     List<Reader> getAllReadersWhereEmailContains(String str);

    boolean isEmailExists(String email);

    void addReader(Reader reader);

    Reader getReaderByEmail(String email);

    void updateReader(Reader reader);

    List<Reader> getReadersAfter(int offset);

    List<ReaderMessageInfo> getReadersToMailDueDate();

    List<ReaderMessageInfo> getReadersToMailReturnDate();
}
