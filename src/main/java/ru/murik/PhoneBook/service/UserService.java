package ru.murik.phone_book3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murik.phone_book3.models.User;
import ru.murik.phone_book3.repository.UserRepo;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public void deleteUser(Long id) {
        User user = userRepo.findById(id).orElseThrow();
        userRepo.delete(user);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow();
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }


}
