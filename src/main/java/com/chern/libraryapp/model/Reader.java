package com.chern.libraryapp.model;

import com.chern.libraryapp.model.enums.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Reader {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Date registrationDate;
    private String phone;
    private Gender gender;
}
