package edu.hw1;

import static java.lang.Math.abs;

public class Task2 {
    private static final int DECIMAL = 10;

    private Task2() {
    }

    public static int countDigits(int number) {
        int count = 0;
        int num = abs(number);

        do {
            num /= DECIMAL;
            count++;
        } while (num > 0);

        return count;
    }
}
