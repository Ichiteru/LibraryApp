package com.chern.libraryapp.model;

import com.chern.libraryapp.model.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class Reader {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Date registrationDate;
    private String phone;
    private Gender gender;

    public Reader(Long id, String email, String firstName, String lastName, Date registrationDate, String phone, Gender gender) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
        this.phone = phone;
        this.gender = gender;
    }
}
