package edu.hw7;

import java.util.List;
import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDBReadWriteLockImpl;
import edu.hw7.Task3.PersonDatabase;
import edu.hw7.Task3.PersonDatabaseImpl;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    public void testSynchronizedAdd() throws InterruptedException {
        PersonDatabase database = new PersonDatabaseImpl();
        Person person1 = new Person(1, "A", "AD", "8");
        Person person2 = new Person(2, "B", "BD", "9");
        Person person3 = new Person(3, "C", "CD", "10");
        Person person4 = new Person(4, "D", "DD", "11");

        Thread thread1 = new Thread(() -> database.add(person1));
        Thread thread2 = new Thread(() -> database.add(person2));
        Thread thread3 = new Thread(() -> {
            database.add(person3);
            database.add(person4);
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(database.findByAddress("AD").getFirst()).isEqualTo(person1);
        assertThat(database.findByName("B").getFirst()).isEqualTo(person2);
        assertThat(database.findByPhone("10").getFirst()).isEqualTo(person3);
    }

    @Test
    public void testFindByInDiffThreads() throws InterruptedException {
        PersonDatabase database = new PersonDatabaseImpl();
        Person person1 = new Person(1, "A", "AD", "8");
        Person person2 = new Person(2, "B", "BD", "9");
        Person person3 = new Person(3, "C", "CD", "10");
        Person person4 = new Person(4, "D", "DD", "11");

        Thread thread1 = new Thread(() -> database.add(person1));
        Thread thread2 = new Thread(() -> {
            database.add(person2);
            database.delete(1);
        });

        Thread thread3 = new Thread(() -> {
            database.add(person3);
            database.add(person4);
            database.delete(4);
            List<Person> phone;
            List<Person> addr;
            synchronized (database) {
                phone = database.findByPhone("8");
                addr = database.findByAddress("AD");
            }
            assertThat(phone).isEqualTo(addr);
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
    }

    @Test
    public void testReadWriteLockFindByInDiffThreads() throws InterruptedException {
        PersonDatabase database = new PersonDBReadWriteLockImpl();
        Person person1 = new Person(1, "A", "AD", "8");
        Person person2 = new Person(2, "B", "BD", "9");
        Person person3 = new Person(3, "C", "CD", "10");
        Person person4 = new Person(4, "D", "DD", "11");

        Thread thread1 = new Thread(() -> database.add(person1));
        Thread thread2 = new Thread(() -> {
            database.add(person2);
            database.delete(1);
        });

        final List<Person>[] person5 = new List[] {null, null};
        Thread thread3 = new Thread(() -> {
            database.add(person3);
            database.add(person4);
            database.delete(4);
            person5[0] = database.findByPhone("8");
            person5[1] = database.findByAddress("AD");
            assertThat(database.findByPhone("8"))
                .isEqualTo(database.findByAddress("AD"));
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(person5[0]).isEqualTo(person5[1]);
    }
}
