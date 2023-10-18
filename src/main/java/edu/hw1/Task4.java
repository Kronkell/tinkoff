package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String str) {
        StringBuilder fixedString = new StringBuilder();
        for (int i = 0; i < str.length() - 1; i += 2) {
            fixedString.append(new char[] {str.charAt(i + 1), str.charAt(i)});
        }
        if (str.length() % 2 == 1) {
            fixedString.append(str.charAt(str.length() - 1));
        }

        return fixedString.toString();
    }
}
