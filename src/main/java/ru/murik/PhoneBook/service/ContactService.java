package ru.murik.phone_book3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murik.phone_book3.models.Contact;
import ru.murik.phone_book3.models.Phone;
import ru.murik.phone_book3.models.PhoneType;
import ru.murik.phone_book3.models.Type;
import ru.murik.phone_book3.repository.ContactRepo;
import ru.murik.phone_book3.repository.UserRepo;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepo contactRepo;

    public List<Contact>getAll() {
        return contactRepo.findAll();
    }

    public Contact get(long id) {
        return contactRepo.findById(id).orElseThrow();
    }

    public void save(Contact contact) {
        contactRepo.save(contact);
    }

    public void delete(Long id) {
        contactRepo.deleteById(id);
    }

    public List<Contact> getByKeyword(String keyword) {
        return contactRepo.findByKeyword(keyword);
    }
}
