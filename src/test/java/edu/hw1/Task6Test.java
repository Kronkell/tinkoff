package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class Task6Test {
    @ParameterizedTest
    @CsvSource({
        "6621, 5",
        "3524, 3",
        "6554, 4",
        "1234, 3",
        "9998, 5",
        "1001, 4"
    })
    public void calculateResultGivenDifferentNumbers(int number, int expected) {
        int actualResult = Task6.countK(number);

        assertThat(actualResult).isEqualTo(expected);
    }

    @Test
    public void shouldThrowExceptionGivenThreeDigitNumber() {
        int number = 456;

        assertThrowsExactly(IllegalArgumentException.class, () -> Task6.countK(number));
    }

    @Test
    public void shouldThrowExceptionGivenSameDigitNumber() {
        int number = 9999;

        assertThrowsExactly(IllegalArgumentException.class, () -> Task6.countK(number));
    }

    @Test
    public void shouldThrowExceptionGivenBiggerNumber() {
        int number = 10000;

        assertThrowsExactly(IllegalArgumentException.class, () -> Task6.countK(number));
    }
}
