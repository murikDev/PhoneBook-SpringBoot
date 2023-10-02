package ru.murik.phone_book3.controllers;

import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.murik.phone_book3.models.Contact;
import ru.murik.phone_book3.models.User;
import ru.murik.phone_book3.repository.UserRepo;
import ru.murik.phone_book3.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/def")
public class UserRestController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public User user (@AuthenticationPrincipal User user) {
        return user;
    }

}
