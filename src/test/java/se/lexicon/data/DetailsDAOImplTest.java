package se.lexicon.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import se.lexicon.model.entity.Details;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DetailsDAOImplTest {

    @Autowired
    DetailsDAOImpl detailsDAO;

    Details details1;
    Details details2;

    @BeforeEach
    void beforeEach(){
        details1 = new Details("mail", "name", LocalDate.parse("2000-01-11"));
        details2 = new Details("mail2", "name2", LocalDate.parse("2000-02-02"));

        detailsDAO.create(details1);
        detailsDAO.create(details2);
    }

    @Test
    void create() {
        assertNotNull(details1);
        assertNotNull(details2);
        assertEquals(1, details1.getDetailsId());
        assertEquals(2, details2.getDetailsId());
    }

    @Test
    void findAll() {
        List<Details> detailsList = detailsDAO.findAll();

        assertTrue(detailsList.size() > 0);
        assertEquals(detailsList.get(0), details1);
        assertEquals(detailsList.get(1), details2);
    }

    @Test
    void findById() {
        Details expected = detailsDAO.findById(details1.getDetailsId());

        //Assert
        assertEquals(expected, details1);
    }

    @Test
    void update() {
        String eMail = "mails";
        String naMe = "naMe";

        details2.setEmail(eMail);
        details2.setName(naMe);
        detailsDAO.update(details2);

        assertEquals(eMail, details2.getEmail());
        assertEquals(naMe, details2.getName());
    }

    @Test
    void delete() {
        int listSizePreDel = detailsDAO.findAll().size();

        detailsDAO.delete(details2.getDetailsId());

        int listSizePostDel = detailsDAO.findAll().size();

        assertNotEquals(listSizePreDel, listSizePostDel);
        assertEquals(listSizePostDel, listSizePreDel-1);
    }
}