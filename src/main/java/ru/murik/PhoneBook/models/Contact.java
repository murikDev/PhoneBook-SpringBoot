package ru.murik.phone_book3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Adyny girizin!")
    //@Size(min = 2, max = 30, message = "Ady 2 harpdan 30 harp aralygynda bolmaly!")
    private String name;

    @Column(name = "surname", nullable = false)
    @NotBlank(message = "Familiyasyny girizin!")
    //@Size(min = 2, max = 30, message = "Familiyasy 2 harpdan 30 harp aralygynda bolmaly!")
    private String surname;

    @JsonIgnore
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<Phone> phones;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Column(name = "dateOfCreated")
    private LocalDate dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDate.now();
    }

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(LocalDate dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

}
