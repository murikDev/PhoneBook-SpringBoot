package ru.murik.phone_book3.models.formModels;

import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.murik.phone_book3.models.Role;
import ru.murik.phone_book3.models.User;

import java.util.List;
import java.util.Set;

public class RegistrationForm {

    @NotBlank(message = "Adyňyzy giriziň!")
    private String username;

    @NotBlank(message = "Açar sözi giriziň!")
    private String password;
    private String confirm;

    private boolean active;
    private boolean admin;
    private boolean delete;
    private boolean create;
    private Set<Role> roles;

    public User toUser(PasswordEncoder passwordEncoder) {
        System.out.println(password);
        return new User(username, passwordEncoder.encode(password));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
