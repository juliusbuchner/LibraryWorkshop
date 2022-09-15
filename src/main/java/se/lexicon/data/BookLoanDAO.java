package se.lexicon.data;

import se.lexicon.model.entity.BookLoan;

import java.util.Collection;
import java.util.List;

public interface BookLoanDAO {
    BookLoan findById(int id);
    List<BookLoan> findAll();
    BookLoan create(BookLoan bookLoan);
    BookLoan update(BookLoan bookLoan);
    void delete(int id);
}
