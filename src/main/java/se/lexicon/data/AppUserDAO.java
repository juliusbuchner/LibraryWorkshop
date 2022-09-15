package se.lexicon.data;

import se.lexicon.model.entity.AppUser;

import java.util.List;

public interface AppUserDAO {
    AppUser findById(int id);
    List<AppUser> findAll();
    AppUser create(AppUser appUser);
    AppUser update(AppUser appUser);
    void delete(int id);
}
