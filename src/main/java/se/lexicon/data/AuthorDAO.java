package se.lexicon.data;

import se.lexicon.model.entity.Author;

import java.util.Collection;


public interface AuthorDAO {

    Author create (Author author);

    Author findById(int id);

    Collection<Author> findAll();

    Author update (Author author);

    void delete(int id);
}