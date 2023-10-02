package ru.murik.phone_book3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


//    @Size(min = 2, message = "Adyňyz 2 harpdan az bolmaly däl!")
    @Column(name = "username", unique = true)
    private String username;


//    @Size(min = 5, message = "Açar söz 5 simwoldan az bolmaly däl!")
    @Column(name = "password")
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Contact> contacts = new ArrayList<>();

    @Column(name = "dateOfCreated")
    private LocalDate dateOfCreated;

    private boolean active;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDate.now();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        getRoles().add(Role.ROLE_USER);
        setActive(true);

    }

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public LocalDate getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(LocalDate dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isCreate() {
        return getRoles().contains(Role.ROLE_CREATE);
    }
    public boolean isAdmin() {
        return getRoles().contains(Role.ROLE_ADMIN);
    }

    public boolean isDelete() {
        return getRoles().contains(Role.ROLE_DELETE);
    }
}
