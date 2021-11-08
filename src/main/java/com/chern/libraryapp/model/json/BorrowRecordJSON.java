package com.chern.libraryapp.model.json;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BorrowRecordJSON {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private String comment;
    private String returnStatus;
    private Integer timePeriod;
}
