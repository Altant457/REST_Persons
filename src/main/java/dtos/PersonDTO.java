package dtos;

import entities.Person;

import java.util.Objects;
public class PersonDTO {
    private Integer id;
    private String fName;
    private String lName;
    private String phone;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.fName = person.getFirstname();
        this.lName = person.getLastname();
        this.phone = person.getPhone();
    }

    public PersonDTO(String firstname, String lastname, String phone) {
        this.fName = firstname;
        this.lName = lastname;
        this.phone = phone;
    }

    public PersonDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
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
        return Objects.equals(this.fName, entity.fName) &&
                Objects.equals(this.lName, entity.lName) &&
                Objects.equals(this.phone, entity.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fName, lName, phone);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "firstname = " + fName + ", " +
                "lastname = " + lName + ", " +
                "phone = " + phone + ")";
    }
}