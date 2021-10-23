package com.chern.libraryapp.model;

import com.chern.libraryapp.model.enums.BookStatus;
import com.chern.libraryapp.model.enums.ReturnedBookStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class BorrowRecord {
    private Long bookId;
    private Long readerId;
    private Reader reader;
//    private String readerEmail;
//    private String readerFirstName;
//    private String readerLastName;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private String comment;
    private Integer timePeriod;
    private ReturnedBookStatus status;
}
