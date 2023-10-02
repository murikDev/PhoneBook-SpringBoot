package ru.murik.phone_book3.models.formModels;

import ru.murik.phone_book3.models.Phone;
import ru.murik.phone_book3.models.PhoneType;

import java.util.List;

public class PhoneList {
    private List<Phone> phones;

    public PhoneList(List<Phone> phones) {
        this.phones = phones;
    }

    public PhoneList() {
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "PhoneList{" +
                "phones=" + phones +
                '}';
    }
}
