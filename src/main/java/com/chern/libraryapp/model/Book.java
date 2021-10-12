package com.chern.libraryapp.model;

import com.chern.libraryapp.model.enums.BookStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Book implements Serializable {
    private String isbn;
    private byte[] cover;
    private String title;
    private String publisher;
    private int pageCount;
    private String description;
    private int totalAmount;
    private BookStatus status;
    private Date publishDate;

    private List<BorrowRecord> borrowers;
    private List<Author> authors;
    private List<Genre> genres;

}
