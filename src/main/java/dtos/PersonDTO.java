package dtos;

import entities.Person;

import java.util.Objects;
public class PersonDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String phone;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
        this.phone = person.getPhone();
    }

    public PersonDTO(String firstname, String lastname, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public PersonDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO entity = (PersonDTO) o;
        return Objects.equals(this.firstname, entity.firstname) &&
                Objects.equals(this.lastname, entity.lastname) &&
                Objects.equals(this.phone, entity.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, phone);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "firstname = " + firstname + ", " +
                "lastname = " + lastname + ", " +
                "phone = " + phone + ")";
    }
}