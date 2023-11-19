package edu.hw5;

import java.util.regex.Pattern;

public class Task4 {
    private Task4() {
    }

    public static boolean containsMandatorySymbol(String input) {
        Pattern pattern = Pattern.compile(".*[~!@#$%^&*|]+.*");

        return pattern.matcher(input).matches();
    }
}
