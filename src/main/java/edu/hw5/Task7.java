package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {
    private Task7() {
    }

    public static boolean condition1(String str) {
        Pattern pattern = Pattern.compile("[01]{2}0[01]+");

        return pattern.matcher(str).matches();
    }

    public static boolean condition2(String str) {
        Pattern pattern = Pattern.compile("^0[01]*0$|^1[01]*1$|^0$|^1$");

        return pattern.matcher(str).matches();
    }

    public static boolean condition3(String str) {
        Pattern pattern = Pattern.compile("^[01]{1,3}$");

        return pattern.matcher(str).matches();
    }
}
