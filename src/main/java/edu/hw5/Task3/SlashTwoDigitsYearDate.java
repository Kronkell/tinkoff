package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class SlashTwoDigitsYearDate extends Middleware {
    @Override
    public Optional<LocalDate> check(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy");
        LocalDate presumedDate;

        try {
            presumedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {

            return checkNext(date);
        }

        return Optional.of(presumedDate);
    }
}
