package ru.murik.phone_book3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.murik.phone_book3.models.Role;
import ru.murik.phone_book3.models.User;
import ru.murik.phone_book3.models.formModels.RegistrationForm;
import ru.murik.phone_book3.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getUsers(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
//        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        System.out.println("========= " + user.isAdmin() +" ===================");
        return "users";
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(Model model, @PathVariable("id")long id) {
        User user = userService.findById(id);
        RegistrationForm form = new RegistrationForm();
        //form.setRoles(user.getRoles());

        for (Role role : user.getRoles()) {
            if (role == Role.ROLE_ADMIN) form.setAdmin(true);
            if (role == Role.ROLE_CREATE) form.setCreate(true);
            if (role == Role.ROLE_DELETE) form.setDelete(true);
        }
        form.setActive(user.isActive());

        model.addAttribute("user", user);
        model.addAttribute("form", form);
        return "editUser";
    }


    @PostMapping("/user/{id}")
    public String updateUser(@PathVariable("id")long id,
                             @ModelAttribute("form")RegistrationForm form) {
        User user = userService.findById(id);

        if (form.isAdmin()) user.getRoles().add(Role.ROLE_ADMIN);
        else user.getRoles().remove(Role.ROLE_ADMIN);

        if (form.isCreate()) user.getRoles().add(Role.ROLE_CREATE);
        else user.getRoles().remove(Role.ROLE_CREATE);

        if (form.isDelete()) user.getRoles().add(Role.ROLE_DELETE);
        else user.getRoles().remove(Role.ROLE_DELETE);

        user.setActive(form.isActive());
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

}
