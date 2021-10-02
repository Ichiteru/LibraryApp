package com.chern.libraryapp.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowRecord {
    private Long bookISBN;
    private String readerEmail;
    private Reader reader;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String comment;
    private int timePeriod;
}
