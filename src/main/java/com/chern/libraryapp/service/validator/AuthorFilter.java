package com.chern.libraryapp.service.validator;

import com.chern.libraryapp.dao.DAOFactory;
import com.chern.libraryapp.model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorFilter {

    public ArrayList<Author> convertToAuthorList(String[] initials){
        ArrayList<Author> listInitials = new ArrayList<>();
        for (String str :
                initials) {
            Author author = new Author();
            String[] firstLastNameArray = str.split(" ");
            author.setFirstName(firstLastNameArray[0]);
            author.setLastName(firstLastNameArray[1]);
            listInitials.add(author);
        }
        return listInitials;
    }

    public List<Author> getNewAuthors(List<Author> authorList){
        List<Author> allAuthors = DAOFactory.authorDAO().getAllAuthors();
        List<Author> al = new ArrayList<>(authorList);
        for (Author author:
                al) {
            if (allAuthors.contains(author))
              authorList.remove(author);
        }
        return authorList;
    }

}
