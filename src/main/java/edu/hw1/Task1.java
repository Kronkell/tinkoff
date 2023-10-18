package edu.hw1;

import java.util.Objects;

public class Task1 {
    private static final int SECONDS = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        if (Objects.equals(time, "") || !time.contains(":")) {
            return -1;
        }

        int minutes = Integer.parseInt(time.split(":")[0]);
        int seconds = Integer.parseInt(time.split(":")[1]);

        if (seconds >= SECONDS) {
            return -1;
        }

        return minutes * SECONDS + seconds;
    }
}
