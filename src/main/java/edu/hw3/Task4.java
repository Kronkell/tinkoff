package edu.hw3;

import java.util.List;

public class Task4 {
    private Task4() {
    }

    public static String convertToRoman(int arabianNumber) {
        final int MULTIPLIER = 2;
        final int FOUR = 4;
        final int NINE = 9;
        final int FIVE = 5;
        final int ONE = 1;
        final int MAX_ROMAN_NUMBER = 3999;
        final List<Character> romanLiterals = List.of('I', 'V', 'X', 'L', 'C', 'D', 'M');

        if (arabianNumber < ONE || arabianNumber > MAX_ROMAN_NUMBER) {
            throw new IllegalArgumentException("There is no such number in Roman numerals!");
        }

        String number = String.valueOf(arabianNumber);
        var answer = new StringBuilder();

        for (int i = 0; i < number.length(); ++i) {
            int digit = Character.getNumericValue(number.charAt(i));
            int listIndex = MULTIPLIER * (number.length() - i - 1);

            if (digit == FOUR) {
                answer.append(romanLiterals.get(listIndex));
                answer.append(romanLiterals.get(listIndex + 1));
            } else if (digit == NINE) {
                answer.append(romanLiterals.get(listIndex));
                answer.append(romanLiterals.get(listIndex + 2));
            } else {
                if (digit >= FIVE) {
                    answer.append(romanLiterals.get(listIndex + 1));
                    answer.append(romanLiterals.get(listIndex).toString().repeat(digit - FIVE));
                } else if (digit >= ONE) {
                    answer.append(romanLiterals.get(listIndex).toString().repeat(digit));
                }
            }
        }

        return answer.toString();
    }
}
