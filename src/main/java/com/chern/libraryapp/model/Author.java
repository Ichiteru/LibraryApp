package com.chern.libraryapp.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Author implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;

}
