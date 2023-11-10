package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class OneWordDate extends Middleware {
    @Override
    public Optional<LocalDate> check(String date) {
        LocalDate now = LocalDate.now();
        LocalDate presumedDate = switch (date) {
            case "tomorrow" -> now.plusDays(1);
            case "today" -> now;
            case "yesterday" -> now.minusDays(1);
            default -> null;
        };

        if (presumedDate == null) {
            return checkNext(date);
        }

        return Optional.of(presumedDate);
    }
}
