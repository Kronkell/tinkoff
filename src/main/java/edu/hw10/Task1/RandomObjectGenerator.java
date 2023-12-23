package edu.hw10.Task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings({"MultipleStringLiterals", "CyclomaticComplexity", "ReturnCount"})
public class RandomObjectGenerator {
    private static final String INTEGER = "Integer";
    private static final String DOUBLE = "Double";
    private static final String STRING = "String";
    private static final String BOOLEAN = "Boolean";
    private static final String LONG = "Long";
    private static final String CHAR = "Character";
    private static final int CHAR_MIN = '0';
    private static final int CHAR_MAX = 'z';

    public RandomObjectGenerator() {
    }

    String getRandomString(boolean isNotNull, boolean isMax, boolean isMin) {
        byte length = (byte) ThreadLocalRandom.current().nextInt(0, Byte.MAX_VALUE);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            sb.append((char) ThreadLocalRandom.current().nextInt(CHAR_MIN, CHAR_MAX));
        }

        if (isNotNull) {
            return sb.toString();
        } else if (isMax) {
            return String.valueOf((char) CHAR_MAX).repeat(length);
        } else if (isMin) {
            return String.valueOf((char) CHAR_MIN);
        }

        int idx = ThreadLocalRandom.current().nextInt(2);
        if (idx == 0) {
            return sb.toString();
        }

        return null;
    }

    Object getRandomObject(Class<?> obj, Annotation[] annotations) {
        int idx = ThreadLocalRandom.current().nextInt(2);
        Object object = null;
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;
        boolean isNotNull = false;
        boolean isMinSet = false;
        boolean isMaxSet = false;

        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = ((Min) annotation).value();
                isMinSet = true;
            }
            if (annotation instanceof Max) {
                max = ((Max) annotation).value();
                isMaxSet = true;
            }
            if (annotation instanceof NotNull) {
                isNotNull = true;
            }
        }

        if (isNotNull) {
            object = switch (obj.getSimpleName()) {
                case INTEGER -> ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
                case STRING -> getRandomString(true, false, false);
                case DOUBLE -> ThreadLocalRandom.current().nextDouble(Double.MIN_VALUE, Double.MAX_VALUE);
                case CHAR -> (char) ThreadLocalRandom.current().nextInt(CHAR_MIN, CHAR_MAX);
                case LONG -> ThreadLocalRandom.current().nextLong(Long.MIN_VALUE, Long.MAX_VALUE);
                case BOOLEAN -> ThreadLocalRandom.current().nextBoolean();
                default -> throw new IllegalStateException("Unexpected value: " + obj.getSimpleName());
            };
        } else if (isMinSet || isMaxSet || idx == 1) {
            object = switch (obj.getSimpleName()) {
                case INTEGER -> ThreadLocalRandom.current().nextInt((int) min, (int) max);
                case STRING -> getRandomString(false, false, false);
                case DOUBLE -> ThreadLocalRandom.current().nextDouble(min, max);
                case CHAR -> (char) ThreadLocalRandom.current().nextInt(CHAR_MIN, CHAR_MAX);
                case LONG -> ThreadLocalRandom.current().nextLong(min, max);
                case BOOLEAN -> ThreadLocalRandom.current().nextBoolean();
                default -> throw new IllegalStateException("Unexpected value: " + obj.getSimpleName());
            };
        }

        return object;
    }

    public Object nextObject(Class<?> clazz)
        throws IllegalAccessException, InstantiationException, InvocationTargetException {

        Constructor<?> constructor = Arrays.stream(clazz.getDeclaredConstructors())
            .max(Comparator.comparing(c -> c.getParameterTypes().length))
            .get();

        List<Object> initArgs = getInitArgs(constructor.getParameterTypes(), constructor.getParameterAnnotations());
        constructor.setAccessible(true);
        return constructor.newInstance(initArgs.toArray());
    }

    public Object nextObject(Class<?> clazz, String fabricMethodName)
        throws InvocationTargetException, IllegalAccessException, InstantiationException {

        Method fabricMethod = Arrays.stream(clazz.getDeclaredMethods())
            .filter(m -> m.getName().equals(fabricMethodName))
            .findFirst()
            .get();

        Constructor<?> constructor = Arrays.stream(clazz.getDeclaredConstructors())
            .max(Comparator.comparing(c -> c.getParameterTypes().length))
            .get();

        List<Object> constructInitArgs =
            getInitArgs(constructor.getParameterTypes(), constructor.getParameterAnnotations());

        List<Object> initArgs = getInitArgs(fabricMethod.getParameterTypes(), fabricMethod.getParameterAnnotations());
        constructor.setAccessible(true);
        fabricMethod.setAccessible(true);

        return fabricMethod.invoke(constructor.newInstance(constructInitArgs.toArray()), initArgs.toArray());
    }

    private List<Object> getInitArgs(Class<?>[] parameterTypes, Annotation[][] parameterAnnotations) {
        List<Object> initArgs = new ArrayList<>();

        for (int i = 0; i < parameterTypes.length; ++i) {
            Annotation[] annotations = parameterAnnotations[i];
            initArgs.add(getRandomObject(parameterTypes[i], annotations));

//            if (annotations.length == 0) {
//                initArgs.add(getRandomObject(parameterTypes[i], false, false, false));
//            } else {
//                Annotation annotation = annotations[0];
//
//                boolean isNotNull = annotation instanceof NotNull;
//                boolean isMax = annotation instanceof Max;
//                boolean isMin = annotation instanceof Min;
//
//                initArgs.add(getRandomObject(parameterTypes[i], isNotNull, isMax, isMin));
//            }
        }

        return initArgs;
    }
}
