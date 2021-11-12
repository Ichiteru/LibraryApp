package com.chern.libraryapp.service.util;

import com.chern.libraryapp.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenreHelper {

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
