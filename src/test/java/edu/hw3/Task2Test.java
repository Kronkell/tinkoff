package edu.hw3;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    static class BracesArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                Arguments.of("()()()", List.of("()", "()", "()")),
                Arguments.of("((()))", List.of("((()))")),
                Arguments.of("((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())")),
                Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))"))
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(BracesArgumentsProvider.class)
    void shouldCalculateResultGivenDifferentArguments(String input, List<String> expectedOutput) {
        List<String> actualOutput = Task2.clusterize(input);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource(
        {"(()))()()()())()()()",
            ")(()()"}
    )
    void shouldThrowExceptionGivenUnbalancedInput(String input) {

        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(input));
    }

}


