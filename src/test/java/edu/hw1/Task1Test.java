package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @ParameterizedTest
    @CsvSource({
        "01:00, 60",
        "13:56, 836",
        "10:59, 659"
    })
    public void calculateResultGivenDifferentNumbers(String time, int expected) {
        int actualNumber = Task1.minutesToSeconds(time);

        assertThat(actualNumber).isEqualTo(expected);
    }

    @Test
    public void shouldReturnMinusOneGivenBiggerSeconds() {
        String time = "12:61";

        int result = Task1.minutesToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void shouldReturnMinusOneGivenEmptyInput() {
        String time = "";

        int result = Task1.minutesToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }
}
