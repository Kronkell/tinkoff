package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDatabaseImpl implements PersonDatabase {
    Map<Integer, Person> mainMap = new HashMap<>();
    Map<String, List<Person>> nameMap = new HashMap<>();
    Map<String, List<Person>> addressMap = new HashMap<>();
    Map<String, List<Person>> phoneMap = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        mainMap.put(person.id(), person);

        addIndex(person.name(), person, Type.NAME);
        addIndex(person.address(), person, Type.ADDRESS);
        addIndex(person.phoneNumber(), person, Type.PHONE);
    }

    private synchronized void addIndex(String key, Person person, Type type) {
        switch (type) {
            case NAME -> nameMap.merge(key, List.of(person), (list1, list2) -> {
                if (!list1.contains(person)) {
                    list1.add(person);
                }
                return list1;
            });
            case ADDRESS -> addressMap.merge(key, List.of(person), (list1, list2) -> {
                if (!list1.contains(person)) {
                    list1.add(person);
                }
                return list1;
            });
            case PHONE -> phoneMap.merge(key, List.of(person), (list1, list2) -> {
                if (!list1.contains(person)) {
                    list1.add(person);
                }
                return list1;
            });
            default -> {
            }
        }
    }

    @Override
    public synchronized void delete(int id) {
        Person person = mainMap.remove(id);

        if (person != null) {
            deleteIndex(person.name(), person, Type.NAME);
            deleteIndex(person.address(), person, Type.ADDRESS);
            deleteIndex(person.phoneNumber(), person, Type.PHONE);
        }
    }

    private synchronized void deleteIndex(String key, Person person, Type type) {
        switch (type) {
            case NAME -> {
                List<Person> list = new ArrayList<>(nameMap.get(key));
                list.remove(person);
                nameMap.put(key, list);
            }
            case ADDRESS -> {
                List<Person> list = new ArrayList<>(addressMap.get(key));
                list.remove(person);
                addressMap.put(key, list);
            }
            case PHONE -> {
                List<Person> list = new ArrayList<>(phoneMap.get(key));
                list.remove(person);
                phoneMap.put(key, list);
            }
            default -> {
            }
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return nameMap.get(name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return addressMap.get(address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return phoneMap.get(phone);
    }

    enum Type {
        NAME,
        ADDRESS,
        PHONE
    }
}
