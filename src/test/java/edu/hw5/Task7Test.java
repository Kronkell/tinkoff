package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @ParameterizedTest
    @CsvSource({
        "0101",
        "110110101011",
        "1000",
        "010101010101010"
    })
    public void condition1ShouldReturnTrue(String number) {
        var actualOutput = Task7.condition1(number);

        assertThat(actualOutput).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({
        "1000001001011",
        "00010101010010",
        "0",
        "1"
    })
    public void condition2ShouldReturnTrue(String number) {
        var actualOutput = Task7.condition2(number);

        assertThat(actualOutput).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({
        "010",
        "1",
        "100",
        "10"
    })
    public void condition3ShouldReturnTrue(String number) {
        var actualOutput = Task7.condition3(number);

        assertThat(actualOutput).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({
        "011",
        "111110101011",
        "1",
        "ve0vQW"
    })
    public void condition1ShouldReturnFalseGivenWrongInputs(String number) {
        var actualOutput = Task7.condition1(number);

        assertThat(actualOutput).isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource({
        "1000001001010",
        "00010101010011",
        "01",
        "10",
        "1wecwec1"
    })
    public void condition2ShouldReturnFalseGivenWrongInputs(String number) {
        var actualOutput = Task7.condition2(number);

        assertThat(actualOutput).isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource({
        "0101",
        "1111",
        "100111",
        "10000",
        "qwe"
    })
    public void condition3ShouldReturnFalseGivenWrongInputs(String number) {
        var actualOutput = Task7.condition3(number);

        assertThat(actualOutput).isEqualTo(false);
    }

}
