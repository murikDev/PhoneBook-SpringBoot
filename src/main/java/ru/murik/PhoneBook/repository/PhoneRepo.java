package ru.murik.phone_book3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.murik.phone_book3.models.Phone;

public interface PhoneRepo extends JpaRepository<Phone, Long> {
}
