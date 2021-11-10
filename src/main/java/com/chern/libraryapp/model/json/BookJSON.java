package com.chern.libraryapp.model.json;

import com.chern.libraryapp.model.Author;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class BookJSON {

    private Long id;
    private String title;
    private String publishDate;
    private int totalAmount;
    private List<Author> authors;

    public BookJSON(Long id, String title, String publishDate, int totalAmount, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
        this.totalAmount = totalAmount;
        this.authors = authors;
    }
}
