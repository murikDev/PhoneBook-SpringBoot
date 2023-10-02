package ru.murik.phone_book3.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.murik.phone_book3.models.Role;
import ru.murik.phone_book3.models.User;
import ru.murik.phone_book3.models.formModels.RegistrationForm;
import ru.murik.phone_book3.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "profile";
    }

    @ModelAttribute(name = "registerForm")
    public RegistrationForm form() {
        return new RegistrationForm();
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid RegistrationForm form, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "registration";
        }
        User user = form.toUser(passwordEncoder);
        System.out.println("======== agza ========");
        System.out.println(form);
        userService.saveUser(user);
        System.out.println("<<<<<<<< DB goshuldy >>>>>>>>");

        authWithHttpServletRequest(request , form.getUsername(), form.getPassword());
        return "redirect:/contact";
    }


    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("registerForm" ,new RegistrationForm());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute RegistrationForm form, HttpServletRequest request) {
        authWithHttpServletRequest(request, form.getUsername(), form.getPassword());
        return "redirect:/contact";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id")Long id) {
        userService.deleteUser(id);
        return "redirect:/contact";
    }

    @PostMapping("/logout")
    public String logoutUser(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/contact";
    }

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            System.out.println("Error while login " + e);
        }
    }

}
