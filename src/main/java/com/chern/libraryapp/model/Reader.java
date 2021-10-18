package com.chern.libraryapp.model;

import com.chern.libraryapp.model.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Reader {
    Long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate registrationDate;
    private String phone;
    private Gender gender;
}
