package ru.murik.phone_book3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murik.phone_book3.models.PhoneType;
import ru.murik.phone_book3.repository.PhoneTypeRepo;

import java.util.List;
import java.util.Objects;

@Service
public class PhoneTypeService {

    @Autowired
    private PhoneTypeRepo phoneTypeRepo;

    public PhoneType findBytype(String phonetype) {
        for (PhoneType type : phoneTypeRepo.findAll()) {
            if (Objects.equals(type.getType(), phonetype)) {
                return type;
            }
        }
        return null;
    }

    public List<PhoneType> getAll() {
        return phoneTypeRepo.findAll();
    }

    public PhoneType getOne(long id) {
        return phoneTypeRepo.getReferenceById(id);
    }
}
