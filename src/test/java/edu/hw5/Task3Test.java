package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import edu.hw5.Task3.Task3;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    static Stream<? extends Arguments> provideArgumentsForRightInputs() {
        return Stream.of(
            Arguments.of(
                "2020-10-10",
                Optional.of(LocalDate.of(2020, 10, 10))
            ),
            Arguments.of(
                "2020-12-2",
                Optional.of(LocalDate.of(2020, 12, 2))
            ),
            Arguments.of(
                "1/3/1976",
                Optional.of(LocalDate.of(1976, 3, 1))
            ),
            Arguments.of(
                "1/3/20",
                Optional.of(LocalDate.of(2020, 3, 1))
            ),
            Arguments.of(
                "tomorrow",
                Optional.of(LocalDate.now().plusDays(1))
            ),
            Arguments.of(
                "today",
                Optional.of(LocalDate.now())
            ),
            Arguments.of(
                "yesterday",
                Optional.of(LocalDate.now().minusDays(1))
            ),
            Arguments.of(
                "1 day ago",
                Optional.of(LocalDate.now().minusDays(1))
            ),
            Arguments.of(
                "2234 days ago",
                Optional.of(LocalDate.now().minusDays(2234))
            )
        );
    }

    static Stream<? extends Arguments> provideArgumentsForWrongInputs() {
        return Stream.of(
            Arguments.of(
                "2020-10-101",
                Optional.empty()
            ),
            Arguments.of(
                "20201-12-02",
                Optional.empty()
            ),
            Arguments.of(
                "1/3-1976",
                Optional.empty()
            ),
            Arguments.of(
                "1/3/201",
                Optional.empty()
            ),
            Arguments.of(
                "tmrw",
                Optional.empty()
            ),
            Arguments.of(
                "Сегодня",
                Optional.empty()
            ),
            Arguments.of(
                "yesterday ",
                Optional.empty()
            ),
            Arguments.of(
                "1 d ays ago",
                Optional.empty()
            ),
            Arguments.of(
                "2234 day ag",
                Optional.empty()
            ),
            Arguments.of(
                "wecwevewf23f",
                Optional.empty()
            ),
            Arguments.of(
                "19-19-19",
                Optional.empty()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForRightInputs")
    public void parseAllDatesGivenStringRepresentations(String date, Optional<LocalDate> expectedOutput) {
        Optional<LocalDate> actualOutput = Task3.parseDate(date);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForWrongInputs")
    public void returnOptionalEmptyGivenWrongStrings(String date, Optional<LocalDate> expectedOutput) {
        Optional<LocalDate> actualOutput = Task3.parseDate(date);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }
}
