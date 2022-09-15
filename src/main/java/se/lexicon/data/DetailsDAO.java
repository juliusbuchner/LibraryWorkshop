package se.lexicon.data;

import se.lexicon.model.entity.Details;

import java.util.List;

public interface DetailsDAO {
    Details findById(int id);
    List<Details> findAll();
    Details create(Details details);
    Details update(Details details);
    void delete(int id);
}
