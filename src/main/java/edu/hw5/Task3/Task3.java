package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class Task3 {
    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String date) {
        Middleware middleware = Middleware.link(
            new DashDate(),
            new SlashFourDigitsYearDate(),
            new SlashTwoDigitsYearDate(),
            new OneWordDate(),
            new DaysAgoDate()
        );

        return middleware.check(date);
    }
}
