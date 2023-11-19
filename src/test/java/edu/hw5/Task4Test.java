package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @ParameterizedTest
    @CsvSource({
        "123451~",
        "veivn| ivnivwieIuvwnv&^",
        "cnwoIenqIo[P[$xnq",
        "!@#$%^&*(()_++}|"
    })
    public void shouldReturnTrueGivenPasswordWithSymbols(String password) {
        var actualOutput = Task4.containsMandatorySymbol(password);

        assertThat(actualOutput).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({
        "123АВЕ777",
        "А123ВГ77",
        "А123ВЕ7777"
    })
    public void shouldReturnFalseGivenPasswordWithoutSymbols(String password) {
        var actualOutput = Task4.containsMandatorySymbol(password);

        assertThat(actualOutput).isEqualTo(false);
    }
}
