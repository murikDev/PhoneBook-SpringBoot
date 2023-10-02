package ru.murik.phone_book3.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "phoneType")
public class PhoneType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "phoneType")
    private List<Phone> phones;

    public PhoneType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String  getType() {
        return type.toString();
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
