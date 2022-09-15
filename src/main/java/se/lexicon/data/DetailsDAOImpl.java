package se.lexicon.data;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.model.entity.Details;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class DetailsDAOImpl implements DetailsDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Details findById(int id) {
        return entityManager.find(Details.class, id);
    }

    @Override
    public List<Details> findAll() {
        TypedQuery<Details> query = entityManager.createQuery("SELECT d FROM Details d", Details.class);
        return query.getResultList();
    }

    @Override
    public Details create(Details details) {
        entityManager.persist(details);
        return details;
    }

    @Override
    public Details update(Details details) {
        entityManager.merge(details);
        return details;
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Details.class, id));
    }
}
