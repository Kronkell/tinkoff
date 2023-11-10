package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @ParameterizedTest
    @CsvSource({
        "010",
        "1101101010111"
    })
    public void condition1ShouldReturnTrue(String number) {
        var actualOutput = Task8.condition1(number);

        assertThat(actualOutput).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({
        "1001",
        "00010",
        "0",
        "11"
    })
    public void condition2ShouldReturnTrue(String number) {
        var actualOutput = Task8.condition2(number);

        assertThat(actualOutput).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({
        "0100",
        "1000000",
        "01010101010",
        "1"
    })
    public void condition3ShouldReturnTrue(String number) {
        var actualOutput = Task8.condition3(number);

        assertThat(actualOutput).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({
        "0100",
        "1000000",
        "01010101010",
        "1111"
    })
    public void condition4ShouldReturnTrue(String number) {
        var actualOutput = Task8.condition4(number);

        assertThat(actualOutput).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({
        "01",
        "1111",
        "10",
        "ve0vQW"
    })
    public void condition1ShouldReturnFalseGivenWrongInputs(String number) {
        var actualOutput = Task8.condition1(number);

        assertThat(actualOutput).isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource({
        "100",
        "0001",
        "01",
        "1",
        "1wecwec1"
    })
    public void condition2ShouldReturnFalseGivenWrongInputs(String number) {
        var actualOutput = Task8.condition2(number);

        assertThat(actualOutput).isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource({
        "0101",
        "11110",
        "10011100",
        "10000",
        "qwe"
    })
    public void condition3ShouldReturnFalseGivenWrongInputs(String number) {
        var actualOutput = Task8.condition3(number);

        assertThat(actualOutput).isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource({
        "11",
        "111"
    })
    public void condition4ShouldReturnFalseGivenWrongInputs(String number) {
        var actualOutput = Task8.condition4(number);

        assertThat(actualOutput).isEqualTo(false);
    }
}
