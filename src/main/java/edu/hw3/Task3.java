package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private Task3() {
    }

    public static Map<Object, Integer> freqDict(List<Object> objectList) {
        Map<Object, Integer> answer = new HashMap<>();

        for (Object obj : objectList) {
            answer.putIfAbsent(obj, 0);
            answer.put(obj, answer.get(obj) + 1);
        }

        return answer;
    }
}
