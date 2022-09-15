package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;

public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    private PersonFacade(){}

    public static PersonFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public PersonDTO addPerson(String fName, String lName, String phone) {
        EntityManager em = getEntityManager();
        try {
            Person newPerson = new Person(fName, lName, phone);
            em.getTransaction().begin();
            em.persist(newPerson);
            em.getTransaction().commit();
            return new PersonDTO(newPerson);
        } finally {
            em.close();
        }
    }

    @Override
    public void deletePerson(int id) {
        EntityManager em = getEntityManager();
        try {
            Person person = em.find(Person.class, id);
            if (person != null) {
                em.getTransaction().begin();
                em.remove(person);
                em.getTransaction().commit();
            } else {
                throw new WebApplicationException(String.format("Person with id = %d doesn't exist", id));
            }
        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO getPerson(int id) {
        EntityManager em = getEntityManager();
        try {
            Person p = em.find(Person.class, id);
            if (p != null) {
                return new PersonDTO(p);
            } else {
                throw new WebApplicationException(String.format("Person with id = %d doesn't exist", id));
            }
        } finally {
            em.close();
        }
    }

    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            return new PersonsDTO(query.getResultList());
        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) {
        EntityManager em = getEntityManager();
        try {
            Person person = em.find(Person.class, p.getId());
            if (person != null) {
                em.getTransaction().begin();
                person.setFirstname(p.getFirstname());
                person.setLastname(p.getLastname());
                person.setPhone(p.getPhone());
                em.merge(person);
                em.getTransaction().commit();
                return new PersonDTO(person);
            } else {
                throw new WebApplicationException(String.format("Person with id = %d doesn't exist", p.getId()));
            }
        } finally {
            em.close();
        }
    }
}
