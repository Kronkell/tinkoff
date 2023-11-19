package edu.hw3;

public class Task1 {
    private Task1() {}

    public static String atbash(String str) {
        var answer = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                answer.append((char) ('z' - ch + 'a'));
            } else if (ch >= 'A' && ch <= 'Z') {
                answer.append((char) ('Z' - ch + 'A'));
            } else {
                answer.append(ch);
            }
        }

        return answer.toString();
    }
}
