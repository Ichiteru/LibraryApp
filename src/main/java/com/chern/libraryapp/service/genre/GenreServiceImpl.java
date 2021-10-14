package com.chern.libraryapp.service.genre;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Genre;
import com.chern.libraryapp.service.genre.GenreService;

import java.util.ArrayList;
import java.util.List;

public class GenreServiceImpl implements GenreService {

    private GenreValidator validator;

    public GenreServiceImpl() {
        this.validator = new GenreValidator();
    }

    @Override
    public List<Genre> getAllGenres(){
        return DAOFactory.genreDao().getAllGenres();
    }

    @Override
    public List<Genre> getBookGenresByISBN(String isbn) {
        return DAOFactory.genreDao().getBookGenresByISBN(isbn);
    }

    public void setNewBookGenres(String[] newGenres, List<Genre> oldGenres, String oldISBN){
        List<Genre> genres = new ArrayList<>(DAOFactory.genreDao().getSelectedGenres(newGenres));
        List<Genre> cloneGenres = new ArrayList<>(genres);
        List<Genre> cloneOldGenres = new ArrayList<>(oldGenres);
        for (Genre g:
             oldGenres) {
            if (genres.contains(g)){
                cloneGenres.remove(g);
                cloneOldGenres.remove(g);
            }
        }
        if (cloneGenres != null || cloneOldGenres != null){
            DAOFactory.genreDao().addSeveralGenresToBook(cloneGenres, oldISBN);
            DAOFactory.genreDao().deleteSeveralGenresFromBook(cloneOldGenres, oldISBN);
        }
        //cloneOldGenres delete from book_genres
    }

}
