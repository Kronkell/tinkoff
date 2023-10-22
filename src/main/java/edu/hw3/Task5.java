package edu.hw3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Task5 {
    private Task5() {
    }

    public static List<Contact> parseContacts(List<String> names, String type) {
        List<Contact> contacts = new ArrayList<>();
        List<String> contact;

        if (names == null) {
            return contacts;
        }

        for (var name : names) {
            contact = List.of(name.split(" "));
            if (contact.size() == 1) {
                contacts.add(new Contact(contact.get(0), ""));
            } else if (contact.size() == 2) {
                contacts.add(new Contact(contact.get(0), contact.get(1)));
            } else {
                throw new IllegalArgumentException("Wrong name!");
            }
        }

        Comparator<Contact> comparator = (o1, o2) -> {
            if (Objects.equals(o1.surname, "")) {
                if (Objects.equals(o2.surname, "")) {
                    return o1.name.compareTo(o2.name);
                } else {
                    return o1.name.compareTo(o2.surname);
                }
            } else if (Objects.equals(o2.surname, "")) {
                return o1.surname.compareTo(o2.name);
            } else {
                return o1.surname.compareTo(o2.surname);
            }
        };

        if (type.equals("ASC")) {
            contacts.sort(comparator);
        } else if (type.equals("DESC")) {
            contacts.sort((comparator.reversed()));
        } else {
            throw new IllegalArgumentException("Wrong sorting type!");
        }

        return contacts;
    }

    public record Contact(String name, String surname) {
    }
}
