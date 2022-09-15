package se.lexicon.data;

import se.lexicon.model.entity.Book;

import java.util.Collection;
import java.util.List;

public interface BookDAO {

    Book findById(int id);

    Collection<Book> findAll();

    Book create (Book book);

    Book update (Book book);

    void delete(int id);
}
