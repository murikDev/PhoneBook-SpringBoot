package ru.murik.phone_book3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.murik.phone_book3.models.Contact;

import java.util.List;

public interface ContactRepo extends JpaRepository<Contact, Long> {

    //Custom query
    @Query(value = "select * from contact s where s.name like %:keyword% or s.surname like %:keyword%", nativeQuery = true)
    List<Contact> findByKeyword(@Param("keyword") String keyword);

}
