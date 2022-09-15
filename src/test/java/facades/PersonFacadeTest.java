package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private PersonDTO pd1,pd2,pd3;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PersonFacade.getInstance(emf);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAll").executeUpdate();
            Person p1 = new Person("Joanne", "Schmidt", "(216) 167-2183");
            Person p2 = new Person("Winston", "Witt", "(414) 318-2894");
            Person p3 = new Person("Kelli", "Walker", "(404) 852-3879");
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
            pd1 = new PersonDTO(p1);
            pd2 = new PersonDTO(p2);
            pd3 = new PersonDTO(p3);
        } finally {
            em.close();
        }
    }

    @Test
    void addPerson() {
        PersonDTO pd4 = facade.addPerson("Ben", "Dixon", "(316) 161-1384");
        PersonsDTO actual = facade.getAllPersons();
        assertThat(actual.getAll(), hasItems(pd2, pd4));
    }

    @Test
    void deletePerson() {
        assertEquals(3, facade.getAllPersons().getAll().size());
        facade.deletePerson(pd3.getId());
        assertEquals(2, facade.getAllPersons().getAll().size());
        assertThat(facade.getAllPersons().getAll(), containsInAnyOrder(pd1, pd2));
    }

    @Test
    void getPerson() {
        PersonDTO actual = facade.getPerson(pd2.getId());
        assertEquals(actual, pd2);
    }

    @Test
    void getAllPersons() {
        PersonsDTO actual = facade.getAllPersons();
        assertThat(actual.getAll(), containsInAnyOrder(pd1, pd2, pd3));
    }

    @Test
    void editPerson() {
        pd1.setFirstname("Anders");
        pd1.setLastname("And");
        facade.editPerson(pd1);
        assertEquals(facade.getPerson(pd1.getId()), pd1);
    }
}