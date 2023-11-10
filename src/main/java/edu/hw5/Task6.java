package edu.hw5;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Task6 {
    private Task6() {
    }

    public static boolean isSubsequence(String s, String t) {

        Pattern pattern = Pattern.compile(Arrays.stream(s.split(""))
            .map(ch -> ch + ".*")
            .reduce(".*", (a, b) -> a + b));

        return pattern.matcher(t).matches();
    }
}
