package ru.murik.phone_book3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.murik.phone_book3.models.Contact;
import ru.murik.phone_book3.models.Phone;
import ru.murik.phone_book3.models.User;
import ru.murik.phone_book3.repository.UserRepo;
import ru.murik.phone_book3.service.ContactService;
import ru.murik.phone_book3.service.PhoneService;

import java.security.Principal;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ContactRestController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/allcontacts")
    public List<Contact> getAllContacts(Principal principal) {
        if (principal != null) {
            User user = userRepo.findByUsername(principal.getName());
            return user.getContacts();
        } else {
            return null;
        }
    }


    @GetMapping("/{id}")
    public Contact contact (@PathVariable("id") long id) {
        return contactService.get(id);
    }

//    @PostMapping()
//    public ResponseEntity<String> save(@RequestBody Contact contact)  {
//            contactService.save(contact);
//            return ResponseEntity.ok("Contact sanawa goshuldy");
//
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")long id) {
        Contact contact = contactService.get(id);
        for (Phone phone : contact.getPhones()) {
            phoneService.delete(phone);
        }
        contactService.delete(id);

        return ResponseEntity.ok("Contact sanawdan  pozuldy");

    }
}
