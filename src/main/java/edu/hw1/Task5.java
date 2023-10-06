package edu.hw1;

public class Task5 {
    private static final int DECIMAL = 10;
    private static final int ELEVEN = 11;

    private Task5() {
    }

    public static boolean isPalindromeDescendant(int number) {

        if (number < 0) {
            throw new IllegalArgumentException("Negative numbers aren't supported!");
        }

        boolean isPalindrome = true;
        String num = String.valueOf(number);

        if (num.length() == 2) {
            if (number % DECIMAL + number / DECIMAL == ELEVEN) {
                return true;
            }

            return num.charAt(0) == num.charAt(1);
        }

        for (int i = 0; i < num.length() / 2; ++i) {
            if (num.charAt(i) != num.charAt(num.length() - i - 1)) {
                isPalindrome = false;
                break;
            }
        }

        StringBuilder desc = new StringBuilder();

        for (int i = 0; i < num.length() - 1; i += 2) {
            desc.append(Character.getNumericValue(num.charAt(i)) + Character.getNumericValue(num.charAt(i + 1)));
        }

        if (num.length() % 2 == 1) {
            desc.append(num.charAt(num.length() - 1));
        }

        return isPalindrome || isPalindromeDescendant(Integer.parseInt(desc.toString()));
    }
}
