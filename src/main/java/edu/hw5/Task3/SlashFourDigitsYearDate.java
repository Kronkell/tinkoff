package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class SlashFourDigitsYearDate extends ChainParser {
    @Override
    public Optional<LocalDate> check(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate presumedDate;

        try {
            presumedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {

            return checkNext(date);
        }

        return Optional.of(presumedDate);
    }
}
