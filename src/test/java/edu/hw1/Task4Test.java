package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @ParameterizedTest
    @CsvSource({
        "123456, 214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde",
        "1, 1"
    })
    public void calculateResultGivenDifferentStrings(String str, String expected) {
        String actualString = Task4.fixString(str);

        assertThat(actualString).isEqualTo(expected);
    }

    @Test
    public void shouldWorkGivenEmptyString() {
        String str = "";

        String result = Task4.fixString(str);

        assertThat(result).isEqualTo("");
    }

}
