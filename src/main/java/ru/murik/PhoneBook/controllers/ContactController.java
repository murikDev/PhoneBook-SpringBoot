package ru.murik.phone_book3.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.murik.phone_book3.models.*;
import ru.murik.phone_book3.models.formModels.PhoneList;
import ru.murik.phone_book3.repository.UserRepo;
import ru.murik.phone_book3.service.ContactService;
import ru.murik.phone_book3.service.PhoneService;
import ru.murik.phone_book3.service.PhoneTypeService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private PhoneTypeService phoneTypeService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private UserRepo userRepo;


    @GetMapping()
    public String getAll(Principal principal, Model model,@AuthenticationPrincipal User user) {
        if (principal != null) {
            model.addAttribute("user", user);
            if (user.getContacts().isEmpty()) {
                System.out.println("contact yook ============");
            } else {
                for (Contact contact : user.getContacts()) {
                    System.out.println(contact);
                }
            }
        } else {
            model.addAttribute("user", null);
        }
        return "home";
    }

    @GetMapping("/{id}")
    public ModelAndView getOne(@PathVariable("id")long id) {
        ModelAndView modelAndView = new ModelAndView("contact.html");
        modelAndView.addObject("contact",contactService.get(id));
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView creat(){
        ModelAndView  modelAndView = new ModelAndView("new.html");

        List<Phone>phones = new ArrayList<>();

        for (PhoneType phoneType : phoneTypeService.getAll()) {
            phones.add(new Phone(phoneType));
        }
        PhoneList phoneList = new PhoneList(phones);

        modelAndView.addObject("contact",new Contact());
        modelAndView.addObject("form", phoneList);
        return modelAndView;
    }

    @PostMapping()
    public ModelAndView save(@ModelAttribute("contact")@Valid Contact contact,
                             BindingResult bindingResult,
                             @ModelAttribute("phone") PhoneList phoneList,
                             Principal principal){

        if (bindingResult.hasErrors()){
            return new ModelAndView("redirect:/contact/new");
        }

        phoneList.getPhones().removeIf(phone -> phone.getPhoneNumber().isEmpty());

        ModelAndView modelAndView = new ModelAndView("redirect:/contact");

        User user = userRepo.findByUsername(principal.getName());
        contact.setUser(user);

        contactService.save(contact);
        for (int i = 0; i < phoneList.getPhones().size(); i++) {
            phoneList.getPhones().get(i).setPhoneType(phoneTypeService.getOne(i+1));
            phoneList.getPhones().get(i).setContact(contact);
        }


        for (Phone phone : phoneList.getPhones()) {
            if (!phone.getPhoneNumber().isEmpty()) {
                phoneService.save(phone);
            }
        }
        return modelAndView;
    }


    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id")long id) {
        Contact contact = contactService.get(id);
        ModelAndView modelAndView = new ModelAndView("edit.html");
        PhoneList phoneList = new PhoneList(contact.getPhones());



        modelAndView.addObject("contact", contact);
        modelAndView.addObject("form", phoneList);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable("id")long id,
                               @ModelAttribute("contact")@Valid Contact contact,
                               BindingResult bindingResult,
                               @ModelAttribute("phoneList")PhoneList phoneList){

        if (bindingResult.hasErrors()){
            return new ModelAndView("redirect:/contact/edit");
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/contact");

        Contact contact1 = contactService.get(id);

        if (!phoneList.getPhones().isEmpty()) {
            for (int i = 0; i < phoneList.getPhones().size(); i++) {
                contact1.getPhones().get(i).setPhoneNumber(phoneList.getPhones().get(i).getPhoneNumber());
            }
        }


        contact1.setName(contact.getName());
        contact1.setSurname(contact.getSurname());

        contactService.save(contact1);
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id")long id) {
        Contact contact = contactService.get(id);
        List<Phone>phones = contact.getPhones();

        for (Phone phone : phones) phoneService.delete(phone);

        contactService.delete(id);
        return new ModelAndView("redirect:/contact");
    }
}
