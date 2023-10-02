package ru.murik.phone_book3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murik.phone_book3.models.Phone;
import ru.murik.phone_book3.repository.PhoneRepo;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepo phoneRepo;

    public Phone getPhones(long id) {
        return phoneRepo.findById(id).orElseThrow();
    }
    public void save(Phone phone) {
        phoneRepo.save(phone);
    }

    public void delete(Phone phone) {
        phoneRepo.delete(phone);
    }
}
