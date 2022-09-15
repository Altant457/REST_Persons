package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Person.deleteAll", query = "delete from Person p")
})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "FIRSTNAME", nullable = false)
    private String firstname;
    @Column(name = "LASTNAME", nullable = false)
    private String lastname;
    @Column(name = "PHONE", nullable = false)
    private String phone;
    @Column(name = "CREATED")
    private Date created;
    @Column(name = "LASTEDITED")
    private Date lastedited;

    public Person(String firstname, String lastname, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public Person(){}

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastedited() {
        return lastedited;
    }

    public void setLastedited(Date lastedited) {
        this.lastedited = lastedited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id) && firstname.equals(person.firstname) && lastname.equals(person.lastname) && phone.equals(person.phone) && Objects.equals(created, person.created) && Objects.equals(lastedited, person.lastedited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, phone, created, lastedited);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", created=" + created +
                ", lastedited=" + lastedited +
                '}';
    }
}
