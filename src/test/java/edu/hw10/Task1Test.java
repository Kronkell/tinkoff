package edu.hw10;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import edu.hw10.Task1.NotNull;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    public void testGenerationViaConstructorPOJO()
        throws InvocationTargetException, IllegalAccessException, InstantiationException {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        var myClass = rog.nextObject(MyClass.class);

        Field[] fields = myClass.getClass().getDeclaredFields();

        assertThat(Integer.class.isAssignableFrom(fields[0].getType())).isTrue();
        assertThat(String.class.isAssignableFrom(fields[1].getType())).isTrue();
    }

    @Test
    public void testGenerationViaFactoryMethodPOJO()
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        var myClass = rog.nextObject(MyClass.class, "create");

        Field[] fields = myClass.getClass().getDeclaredFields();

        assertThat(Integer.class.isAssignableFrom(fields[0].getType())).isTrue();
        assertThat(String.class.isAssignableFrom(fields[1].getType())).isTrue();
    }

    @Test
    public void testRecordGeneration()
        throws InvocationTargetException, IllegalAccessException, InstantiationException {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        var myClass = rog.nextObject(MyRecord.class);

        Field[] fields = myClass.getClass().getDeclaredFields();

        assertThat(Integer.class.isAssignableFrom(fields[0].getType())).isTrue();
        assertThat(String.class.isAssignableFrom(fields[1].getType())).isTrue();
    }

    @Test
    public void testNotNullAnnotationRecord()
        throws InvocationTargetException, IllegalAccessException, InstantiationException {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        var myClass = rog.nextObject(MyRecord.class);

        Field[] fields = myClass.getClass().getDeclaredFields();

        assertThat(fields[0].get(myClass)).isNotNull();
        assertThat(fields[1].get(myClass)).isNotNull();
    }

    @Test
    public void testNotNullAnnotationPOJO()
        throws InvocationTargetException, IllegalAccessException, InstantiationException {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        var myClass = rog.nextObject(MyClass.class);

        Field[] fields = myClass.getClass().getDeclaredFields();

        assertThat(fields[0].get(myClass)).isNotNull();
        assertThat(fields[1].get(myClass)).isNotNull();
    }


    record MyRecord(@NotNull Integer a, @NotNull String b) {
    }

    static class MyClass {
        Integer a;
        String b;

        public MyClass(@NotNull Integer a, @NotNull String b) {
            this.a = a;
            this.b = b;
        }

        public MyClass create(@NotNull Integer a, @NotNull String b) {
            return new MyClass(a, b);
        }
    }
}
