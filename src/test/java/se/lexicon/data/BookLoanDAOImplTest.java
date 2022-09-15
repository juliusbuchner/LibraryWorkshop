package se.lexicon.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import se.lexicon.model.entity.AppUser;
import se.lexicon.model.entity.Book;
import se.lexicon.model.entity.BookLoan;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookLoanDAOImplTest {

    @Autowired
    BookLoanDAOImpl bookLoanDAO;
    @Autowired
    BookDAO bookDAO;
    @Autowired
    AppUserDAO appUserDAO;

    BookLoan bookLoan1;
    BookLoan bookLoan2;
    Book book1;
    Book book2;
    AppUser appUser1;
    AppUser appUser2;


    @BeforeEach
    void beforeEach(){
        book1 = new Book("1469", "Javascript", 11);
        book2 = new Book("1234", "Java development", 8);
        bookDAO.create(book1);
        bookDAO.create(book2);

        appUser1 = new AppUser("userName1", "passWord!", LocalDate.parse("2022-09-14"), "mail1", "name2", LocalDate.MIN);
        appUser2 = new AppUser("userName2", "passWord2!", LocalDate.parse("2022-09-12"), "mail2", "name2", LocalDate.MIN);
        appUserDAO.create(appUser1);
        appUserDAO.create(appUser2);

        bookLoan1 = new BookLoan(LocalDate.now(), LocalDate.MAX, appUser1, book1);
        bookLoan2 = new BookLoan(LocalDate.now(), LocalDate.MAX, appUser2, book2);
        bookLoanDAO.create(bookLoan1);
        bookLoanDAO.create(bookLoan2);

        bookLoan1.setBorrower(appUser1);
        bookLoan1.setBook(book1);
        bookLoanDAO.update(bookLoan1);

        bookLoan2.setBorrower(appUser2);
        bookLoan2.setBook(book2);
        bookLoanDAO.update(bookLoan2);
    }

    @Test
    void create() {
        assertNotNull(bookLoan1);
        assertNotNull(bookLoan2);
        assertEquals(1, bookLoan1.getLoanId());
        assertEquals(2, bookLoan2.getLoanId());
    }

    @Test
    void findAll() {
        Collection<BookLoan> bookLoansList = bookLoanDAO.findAll();

        //Assert
        assertTrue(bookLoansList.size() > 0);
        assertEquals(bookLoansList.size(), 2);
        assertEquals(bookLoansList.iterator().next().getBorrower(), bookLoan1.getBorrower());
        assertEquals(bookLoansList.iterator().next().getBook(), bookLoan1.getBook());
    }

    @Test
    void findById() {
        BookLoan expected = bookLoanDAO.findById(bookLoan1.getLoanId());

        //Assert
        assertEquals(expected.getBorrower(), bookLoan1.getBorrower());
        assertEquals(expected.getBook(), bookLoan1.getBook());
    }

    @Test
    void update() {
        boolean returned = true;
        LocalDate dueDate = LocalDate.MIN;

        bookLoan2.setReturned(returned);
        bookLoan2.setDueDate(dueDate);
        bookLoanDAO.update(bookLoan2);

        assertTrue(bookLoan2.isReturned());
        assertEquals(dueDate, bookLoan2.getDueDate());
    }

    @Test
    void delete() {
        int listSizePreDel = bookLoanDAO.findAll().size();

        bookLoanDAO.delete(bookLoan2.getLoanId());

        int listSizePostDel = bookLoanDAO.findAll().size();

        assertNotEquals(listSizePreDel, listSizePostDel);
        assertEquals(listSizePostDel, listSizePreDel-1);
    }
}