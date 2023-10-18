package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class Task5Test {

    @ParameterizedTest
    @CsvSource({
        "11211230, true",
        "13001120, true",
        "23336014, true",
        "11, true",
        "1, true"
    })
    public void calculateResultGivenDifferentNumbers(int number, boolean expected) {
        boolean actualResult = Task5.isPalindromeDescendant(number);

        assertThat(actualResult).isEqualTo(expected);
    }

    @Test
    public void shouldWorkGivenOddLengthNumber() {
        int number = 398;

        boolean result = Task5.isPalindromeDescendant(number);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldThrowExceptionGivenNegativeNumber() {
        int number = -999;

        assertThrowsExactly(IllegalArgumentException.class, () -> Task5.isPalindromeDescendant(number));
    }
}
