package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    public static List<String> clusterize(String braces) {
        List<String> answer = new ArrayList<>();
        int balance = 0;
        int left = 0;
        for (int i = 0; i < braces.length(); ++i) {
            if (braces.charAt(i) == '(') {
                balance++;
            } else if (braces.charAt(i) == ')') {
                balance--;
            }

            if (balance == 0) {
                answer.add(braces.substring(left, i + 1));
                left = i + 1;
            }
        }

        if (balance != 0) {
            throw new IllegalArgumentException("Input is not balanced!");
        } else {
            return answer;
        }
    }
}
