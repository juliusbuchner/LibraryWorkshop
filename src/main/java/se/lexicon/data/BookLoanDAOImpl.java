package se.lexicon.data;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.model.entity.BookLoan;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

@Transactional
@Repository
public class BookLoanDAOImpl implements BookLoanDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BookLoan findById(int id) {

        return entityManager.find(BookLoan.class, id);
    }

    @Override
    public List<BookLoan> findAll() {
        TypedQuery<BookLoan> query = entityManager.createQuery("SELECT b FROM BookLoan b", BookLoan.class);
        return query.getResultList();
    }

    @Override
    public BookLoan create(BookLoan bookLoan) {
        entityManager.persist(bookLoan);
        return bookLoan;
    }

    @Override
    public BookLoan update(BookLoan bookLoan) {
        entityManager.merge(bookLoan);
        return bookLoan;
    }

    @Override
    public void delete(int id) {
        entityManager.remove(findById(id));
    }
}
