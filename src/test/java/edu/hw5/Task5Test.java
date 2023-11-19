package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @ParameterizedTest
    @CsvSource({
        "А123ВЕ777",
        "О777ОО177"
    })
    public void shouldReturnTrueGivenCorrectPlateNumbers(String number) {
        var actualOutput = Task5.isRightPlate(number);

        assertThat(actualOutput).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({
        "123АВЕ777",
        "А123ВГ77",
        "А123ВЕ7777",
        "А123ВЕ777А123ВЕ777"
    })
    public void shouldReturnFalseGivenIncorrectPlateNumbers(String number) {
        var actualOutput = Task5.isRightPlate(number);

        assertThat(actualOutput).isEqualTo(false);
    }
}
