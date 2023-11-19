package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task1 {
    private Task1() {
    }

    @SuppressWarnings("MultipleStringLiterals")
    public static String averageTimePerSession(List<String> timeBounds) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        LocalDateTime startTime;
        LocalDateTime finishTime;
        long sumOfAllMinutes = 0L;

        for (var timeBound : timeBounds) {
            startTime = LocalDateTime.parse(timeBound.split(" - ")[0], formatter);
            finishTime = LocalDateTime.parse(timeBound.split(" - ")[1], formatter);
            sumOfAllMinutes += Duration.between(startTime, finishTime).toMinutes();
        }

        long averageMinutesPerSession;
        if (timeBounds.isEmpty()) {
            averageMinutesPerSession = 0;
        } else {
            averageMinutesPerSession = sumOfAllMinutes / timeBounds.size();
        }

        return String.format(
            "%dч %dм",
            Duration.ofMinutes(averageMinutesPerSession).toHoursPart(),
            Duration.ofMinutes(averageMinutesPerSession).toMinutesPart()
        );
    }
}
