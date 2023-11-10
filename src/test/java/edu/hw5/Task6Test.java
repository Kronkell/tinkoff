package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @ParameterizedTest
    @CsvSource({
        "abc, achfdbaabgabcaabg",
        "ag, amvomomeemgmeoem",
        "1234, mkm1wevkwjnv1weivevkmo2odkmo3eovkm4"
    })
    public void shouldReturnTrueGivenSubsequence(String s, String t) {
        var actualOutput = Task6.isSubsequence(s, t);

        assertThat(actualOutput).isEqualTo(true);
    }
    @ParameterizedTest
    @CsvSource({
        "abc, ac",
        "ag, amvomomem",
        "1234, mkm1wevkwjnv1weivevkmoodkmo3eovkm4"
    })
    public void shouldReturnFalseGivenNotSubsequence(String s, String t) {
        var actualOutput = Task6.isSubsequence(s, t);

        assertThat(actualOutput).isEqualTo(false);
    }
}
