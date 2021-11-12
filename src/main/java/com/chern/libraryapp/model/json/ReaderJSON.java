package com.chern.libraryapp.model.json;

import com.chern.libraryapp.model.enums.Gender;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class ReaderJSON {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String registrationDate;
    private String phone;
    private Gender gender;

    public ReaderJSON(Long id, String email, String firstName, String lastName, String registrationDate, String phone, Gender gender) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
        this.phone = phone;
        this.gender = gender;
    }
}
