package com.chern.libraryapp.service.validator;

import com.chern.libraryapp.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenreValidator {

    public List<Genre> transformToList(String[] genres){
        ArrayList<Genre> genreArrayList = new ArrayList<>();
        for (String g :
                genres) {
            Genre genre = new Genre();
            genre.setName(g);
            genreArrayList.add(genre);
        }
        return genreArrayList;
    }
}
