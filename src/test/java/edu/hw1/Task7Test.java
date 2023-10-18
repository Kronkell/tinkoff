package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task7Test {

    @ParameterizedTest
    @CsvSource({
        "0, 0, 0",
        "8, 1, 1",
        "16, 1, 1",
        "17, 2, 6",
        "15, 12, 15",
        "124, 2, 115"
    })
    public void calculateResultForLeftShiftGivenDifferentNumbers(int n, int shift, int expected) {
        int actualResult = Task7.rotateLeft(n, shift);

        assertThat(actualResult).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, 0",
        "8, 1, 4",
        "16, 1, 8",
        "17, 2, 12",
        "15, 12, 15",
        "124, 2, 31",
        "124, 3, 79"
    })
    public void calculateResultForRightShiftGivenDifferentNumbers(int n, int shift, int expected) {
        int actualResult = Task7.rotateRight(n, shift);

        assertThat(actualResult).isEqualTo(expected);
    }

    @Test
    public void shouldThrowExceptionGivenNegativeInputRight() {
        int n = -2;
        int shift = -12;

        assertThrows(IllegalArgumentException.class, () -> Task7.rotateRight(n, shift));
    }

    @Test
    public void shouldThrowExceptionGivenNegativeInputLeft() {
        int n = -1;
        int shift = -11;

        assertThrows(IllegalArgumentException.class, () -> Task7.rotateLeft(n, shift));
    }
}
