package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonDBReadWriteLockImpl implements PersonDatabase {
    Map<Integer, Person> mainMap = new HashMap<>();
    Map<String, List<Person>> nameMap = new HashMap<>();
    Map<String, List<Person>> addressMap = new HashMap<>();
    Map<String, List<Person>> phoneMap = new HashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            mainMap.put(person.id(), person);
            addIndex(person.name(), person, Type.NAME);
            addIndex(person.address(), person, Type.ADDRESS);
            addIndex(person.phoneNumber(), person, Type.PHONE);
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void addIndex(String key, Person person, Type type) {
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
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = mainMap.remove(id);
            if (person != null) {
                deleteIndex(person.name(), person, Type.NAME);
                deleteIndex(person.address(), person, Type.ADDRESS);
                deleteIndex(person.phoneNumber(), person, Type.PHONE);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void deleteIndex(String key, Person person, Type type) {
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
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return nameMap.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return addressMap.get(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return phoneMap.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }

    enum Type {
        NAME,
        ADDRESS,
        PHONE
    }
}
