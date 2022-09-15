package se.lexicon.data;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.model.entity.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class AppUserDAOImpl implements AppUserDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AppUser findById(int id) {
        return entityManager.find(AppUser.class, id);
    }

    @Override
    public List<AppUser> findAll() {
        TypedQuery<AppUser> query = entityManager.createQuery("SELECT u FROM AppUser u", AppUser.class);
        return  query.getResultList();
    }

    @Override
    public AppUser create(AppUser appUser) {
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    public AppUser update(AppUser appUser) {
        entityManager.merge(appUser);
        return appUser;
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(AppUser.class, id));
    }
}
