package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import errorhandling.PersonNotFoundException;

public interface IPersonFacade {
    public PersonDTO addPerson(String fName, String lName, String phone);
    public boolean deletePerson(int id) throws PersonNotFoundException;
    public PersonDTO getPerson(int id) throws PersonNotFoundException;
    public PersonsDTO getAllPersons();
    public PersonDTO editPerson(PersonDTO p) throws PersonNotFoundException;
}
