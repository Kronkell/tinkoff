package edu.hw5;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    static class TimeStampArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                Arguments.of(
                    List.of(
                        "2022-03-12, 20:20 - 2022-03-12, 23:50",
                        "2022-04-01, 21:30 - 2022-04-02, 01:20"
                    ),
                    "3ч 40м"
                ),
                Arguments.of(
                    List.of(
                        "2022-03-12, 20:20 - 2022-03-12, 23:50",
                        "2022-04-01, 21:30 - 2022-04-02, 01:20",
                        "2021-06-03, 12:00 - 2021-06-03, 23:59",
                        "2021-12-31, 12:00 - 2022-01-01, 17:50"
                    ),
                    "12ч 17м"
                ),
                Arguments.of(
                    List.of(
                        "2020-10-28, 11:00 - 2020-10-28, 12:50",
                        "2020-10-15, 19:05 - 2020-10-15, 20:00",
                        "2021-12-31, 12:00 - 2021-12-31, 12:30"
                    ),
                    "1ч 5м"
                )
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(TimeStampArgumentsProvider.class)
    public void calculateResultGivenDifferentInputs(List<String> inputList, String expectedOutput) throws ParseException {
        String actualOutput = Task1.averageTimePerSession(inputList);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }
}
