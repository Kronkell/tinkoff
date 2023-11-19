package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {
    private Task8() {
    }

    //odd length
    public static boolean condition1(String str) {
        Pattern pattern = Pattern.compile("^[01]([01][01])*$");

        return pattern.matcher(str).matches();
    }

    //starts with 0 and of odd length or ...
    public static boolean condition2(String str) {
        Pattern pattern = Pattern.compile("^0([01][01])*$|^1[01]([01][01])*$");

        return pattern.matcher(str).matches();
    }

    //is number of 0s divisible by 3
    public static boolean condition3(String str) {
        Pattern pattern = Pattern.compile("^(1*01*01*01*)*$|^1$");

        return pattern.matcher(str).matches();
    }

    //any string except 11 and 111
    public static boolean condition4(String str) {
        Pattern pattern = Pattern.compile("^(?!11$|111$).*");

        return pattern.matcher(str).matches();
    }
}
