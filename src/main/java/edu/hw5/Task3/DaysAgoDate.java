package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaysAgoDate extends Middleware {
    @Override
    public Optional<LocalDate> check(String date) {
        LocalDate now = LocalDate.now();
        Pattern pattern = Pattern.compile("(\\d+) days? ago");
        Matcher matcher = pattern.matcher(date);

        if (matcher.matches()) {
            return Optional.of(now.minusDays(Integer.parseInt(matcher.group(1))));
        } else {
            return checkNext(date);
        }
    }
}
