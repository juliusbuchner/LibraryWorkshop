package se.lexicon.data;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.model.entity.Author;
import se.lexicon.model.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;


@Transactional
@Repository
public class AuthorDAOImpl implements AuthorDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author create(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    public Author findById(int id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public Collection<Author> findAll() {
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
        return  query.getResultList();
    }

    @Override
    public Author update(Author author) {
        entityManager.merge(author);
        return author;
    }

    @Override
    public void delete(int id) {
        Author author = findById(id);
        entityManager.remove(entityManager.find(Author.class, id));
    }
}