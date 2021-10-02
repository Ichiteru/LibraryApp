package com.chern.libraryapp.model;

import lombok.Data;

@Data
public class Author {
    private Long id;
    private Long bookIsbn;
    private String firstName;
    private String lastName;
}
