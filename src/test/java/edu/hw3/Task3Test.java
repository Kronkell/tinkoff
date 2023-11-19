package edu.hw3;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    static class ObjectsArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("bb", 2, "a", 2)),
                Arguments.of(List.of("this", "and", "that", "and"), Map.of("that", 1, "and", 2, "this", 1)),
                Arguments.of(List.of("код", "код", "код", "bug"), Map.of("код", 3, "bug", 1)),
                Arguments.of(List.of(1, 1, 2, 2), Map.of(1, 2, 2, 2)),
                Arguments.of(List.of(List.of(), List.of(), List.of(), List.of()), Map.of(List.of(), 4))
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ObjectsArgumentsProvider.class)
    public void shouldCalculateResultGivenDifferentArgs(List<Object> input, Map<Object, Integer> expectedOutput) {
        var actualOutput = Task3.freqDict(input);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

}
