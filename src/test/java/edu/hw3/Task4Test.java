package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {
    @ParameterizedTest
    @CsvSource({
        "1, I",
        "2, II",
        "12, XII",
        "16, XVI",
        "1234, MCCXXXIV",
        "3999, MMMCMXCIX",
        "2736, MMDCCXXXVI",
        "3916, MMMCMXVI",
        "2001, MMI",
        "2949, MMCMXLIX",
        "1007, MVII"
    })
    public void shouldCalculateResultGivenDifferentInputs(int number, String expectedOutput) {
        String actualOutput = Task4.convertToRoman(number);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "0",
        "4000",
        "-4"
    })
    public void shouldThrowExceptionGivenIncorrectInput(int number) {
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(number));
    }
}
