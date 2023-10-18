package edu.hw1;

import java.util.Arrays;
import java.util.OptionalInt;

public class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] arr1, int[] arr2) {
        OptionalInt min1 = Arrays.stream(arr1).min();
        OptionalInt min2 = Arrays.stream(arr2).min();
        OptionalInt max1 = Arrays.stream(arr1).max();
        OptionalInt max2 = Arrays.stream(arr2).max();

        if (min1.isPresent() && min2.isPresent()) {
            return (min1.getAsInt() > min2.getAsInt()) && (max1.getAsInt() < max2.getAsInt());
        } else {
            return false;
        }
    }
}
