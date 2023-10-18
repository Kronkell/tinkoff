package edu.hw1;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task6 {
    private Task6() {
    }

    public static final int KAPREKAR = 6174;
    public static final int FOUR_DIGITS = 1000;
    public static final int FIVE_DIGITS = 9999;
    public static final int DECIMAL = 10;

    public static int countK(int number) {
        if (number < FOUR_DIGITS || number > FIVE_DIGITS) {
            throw new IllegalArgumentException("Wrong number!");
        }

        if (number == KAPREKAR) {
            return 0;
        }

        char[] numArray = String.valueOf(number).toCharArray();
        Character[] num = IntStream.range(0, numArray.length)
            .mapToObj(i -> numArray[i])
            .toArray(Character[]::new);

        Arrays.sort(num);
        int asc = Integer.parseInt(Arrays.stream(num).map(String::valueOf).collect(Collectors.joining()));

        Arrays.sort(num, Collections.reverseOrder());
        int desc = Integer.parseInt(Arrays.stream(num).map(String::valueOf).collect(Collectors.joining()));

        int diff = Math.abs(asc - desc);
        if (diff < FOUR_DIGITS) {
            diff *= DECIMAL;
        }

        return 1 + countK(diff);
    }
}
