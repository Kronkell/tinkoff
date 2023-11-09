package edu.hw5;

import java.util.regex.Pattern;

public class Task4 {
    boolean containsMandatorySymbol(String input) {
        Pattern pattern = Pattern.compile("~!@#\\$%\\^&*\\|");

        return pattern.matcher(input).find();
    }
}
