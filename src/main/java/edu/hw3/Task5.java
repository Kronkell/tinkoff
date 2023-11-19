package edu.hw3;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Task5 {
    private Task5() {
    }

    public static List<Contact> parseContactsWithStreams(List<String> names, String type) {
        if (names == null) {
            return List.of();
        }

        Comparator<Contact> comparator = (o1, o2) -> {
            if (Objects.equals(o1.getSurname(), null)) {
                if (Objects.equals(o2.getSurname(), null)) {
                    return o1.getName().compareTo(o2.getName());
                } else {
                    return o1.getName().compareTo(o2.getSurname());
                }
            } else if (Objects.equals(o2.getSurname(), null)) {
                return o1.getSurname().compareTo(o2.getName());
            } else {
                return o1.getSurname().compareTo(o2.getSurname());
            }
        };

        if (type.equals("ASC")) {
            return names.stream().map(Contact::new).sorted(comparator).toList();
        } else if (type.equals("DESC")) {
            return names.stream().map(Contact::new).sorted(comparator.reversed()).toList();
        } else {
            throw new IllegalArgumentException("Wrong sorting type!");
        }
    }

    public static final class Contact {
        private final String name;
        private final String surname;

        public Contact(String input) {
            var contact = List.of(input.split(" "));
            if (contact.size() == 1) {
                name = contact.get(0);
                surname = null;
            } else if (contact.size() == 2) {
                name = contact.get(0);
                surname = contact.get(1);
            } else {
                throw new IllegalArgumentException("Wrong name!");
            }
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }
    }
}
