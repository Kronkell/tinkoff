package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @ParameterizedTest
    @CsvSource({
        "123456, 6",
        "4666, 4",
        "544, 3",
        "0, 1"
    })
    public void calculateResultGivenDifferentNumbers(int number, int expected) {
        int actualNumber = Task2.countDigits(number);

        assertThat(actualNumber).isEqualTo(expected);
    }

    @Test
    public void shouldWorkGivenNegativeNumbers() {
        int number = -1234;

        int count = Task2.countDigits(number);

        assertThat(count).isEqualTo(4);
    }
}
