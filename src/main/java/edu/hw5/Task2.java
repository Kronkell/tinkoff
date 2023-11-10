package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class Task2 {
    private Task2() {
    }

    public static List<LocalDate> findFridayThirteens(int year) {
        var answer = new ArrayList<LocalDate>();
        LocalDate date = LocalDate.of(year, 1, 13);
        LocalDate finishDate = LocalDate.of(year, 12, 31);

        while (date.isBefore(finishDate)) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                answer.add(date);
            }
            date = date.plusMonths(1);
        }

        return answer;
    }

    public static LocalDate findClosestFridayThirteen(LocalDate date) {
        LocalDate currentDate = date.plusDays(1);

        while (currentDate.getDayOfMonth() != 13) {
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }

        return currentDate;
    }
}
