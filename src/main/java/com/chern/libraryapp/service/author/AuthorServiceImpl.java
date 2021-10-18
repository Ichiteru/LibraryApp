package com.chern.libraryapp.service.author;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Author;

import java.util.*;

public class AuthorServiceImpl implements AuthorService {

    private AuthorValidator validator;

    public AuthorServiceImpl(){
        this.validator = new AuthorValidator();
    }

    @Override
    public List<Author> getAllAuthors() {
        return DAOFactory.authorDAO().getAllAuthors();
    }

    public void addSeveralAuthors(String[] authors){
        ArrayList<Author> newBookAuthors = validator.convertToAuthorList(authors); // конвертированный []
        if (authors != null)
            DAOFactory.authorDAO().addSeveralAuthors(validator.getNewAuthors(newBookAuthors)); // записываем в бд новых авторов

    }

    public void setNewBookAuthors(String[] newAuthors, List<Author> oldAuthors, String oldISBN){
        addSeveralAuthors(newAuthors);
        ArrayList<Author> newAuthorList = validator.convertToAuthorList(newAuthors); // без id
        List<Author> newAuthorListWithId = new ArrayList<>(DAOFactory.authorDAO().getSelectedAuthors(newAuthorList)); // получаем из бд авторов с id
        List<Author> cloneOldAuthors = new ArrayList<>(oldAuthors);
        for (Author a:
                cloneOldAuthors) {
            if (newAuthorList.contains(a)){
                 newAuthorListWithId.remove(a);
                oldAuthors.remove(a);
            }
        }
        if (oldAuthors != null || newAuthorListWithId != null){
            DAOFactory.authorDAO().addSeveralAuthorsToBook(newAuthorListWithId, oldISBN);
            DAOFactory.authorDAO().deleteSeveralAuthorsFromBook(oldAuthors, oldISBN);
        }
        //cloneOldGenres delete from book_genres
    }

//    public void setNewBookAuthors(String[] newAuthors, String oldISBN){
//        addSeveralAuthors(newAuthors); // проверка на новых авторов и запись из в бд
//        ArrayList<Author> newAuthorList = validator.convertToAuthorList(newAuthors); // без id
//        List<Author> newAuthorListWithId = new ArrayList<>(DAOFactory.authorDAO().getSelectedAuthors(newAuthorList)); // получаем из бд авторов с id
//        List<Author> cloneOldAuthors = new ArrayList<>(oldAuthors);
//        for (Author a:
//                cloneOldAuthors) {
//            if (newAuthorList.contains(a)){
//                newAuthorListWithId.remove(a);
//                oldAuthors.remove(a);
//            }
//        }
//        if (oldAuthors != null || newAuthorListWithId != null){
//            DAOFactory.authorDAO().addSeveralAuthorsToBook(newAuthorListWithId, oldISBN);
//            DAOFactory.authorDAO().deleteSeveralAuthorsFromBook(oldAuthors, oldISBN);
//        }
//        //cloneOldGenres delete from book_genres
//    }

    @Override
    public List<Author> getBookAuthorsById(Long id) {
        return DAOFactory.authorDAO().getBookAuthorsById(id);
    }
}
