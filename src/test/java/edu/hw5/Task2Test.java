package edu.hw5;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    public static class DateYearProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                Arguments.of(
                    1925,
                    List.of(
                        LocalDate.of(1925, 2, 13),
                        LocalDate.of(1925, 3, 13),
                        LocalDate.of(1925, 11, 13)
                    )
                ),
                Arguments.of(
                    2024,
                    List.of(
                        LocalDate.of(2024, 9, 13),
                        LocalDate.of(2024, 12, 13)
                    )
                ),
                Arguments.of(
                    2028,
                    List.of(
                        LocalDate.of(2028, 10, 13)
                    )
                )
            );
        }
    }

    public static class DatesProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                Arguments.of(
                    LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13)
                ),
                Arguments.of(
                    LocalDate.of(2024, 9, 13),
                    LocalDate.of(2024, 12, 13)
                )
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(DateYearProvider.class)
    public void returnAllFridayThirteenInGivenYear(int year, List<LocalDate> expectedOutput) {
        List<LocalDate> actualOutput = Task2.findFridayThirteens(year);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @ArgumentsSource(DatesProvider.class)
    public void returnClosestFridayThirteen(LocalDate date, LocalDate expectedOutput) {
        LocalDate actualOutput = Task2.findClosestFridayThirteen(date);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }
}
