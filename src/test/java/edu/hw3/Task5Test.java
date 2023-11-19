package edu.hw3;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import static edu.hw3.Task5.parseContactsWithStreams;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    static class ContactsArgumentProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                Arguments.of(
                    List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"),
                    "ASC",
                    List.of(
                        new Task5.Contact("Thomas Aquinas"),
                        new Task5.Contact("Rene Descartes"),
                        new Task5.Contact("David Hume"),
                        new Task5.Contact("John Locke")
                    )
                ),
                Arguments.of(
                    List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss"),
                    "DESC",
                    List.of(
                        new Task5.Contact("Carl Gauss"),
                        new Task5.Contact("Leonhard Euler"),
                        new Task5.Contact("Paul Erdos")
                    )
                ),
                Arguments.of(
                    List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss", "Max Euwe", "Paul Morphy", "Magnus"),
                    "DESC",
                    List.of(
                        new Task5.Contact("Paul Morphy"),
                        new Task5.Contact("Magnus"),
                        new Task5.Contact("Carl Gauss"),
                        new Task5.Contact("Max Euwe"),
                        new Task5.Contact("Leonhard Euler"),
                        new Task5.Contact("Paul Erdos")
                    )
                ),
                Arguments.of(
                    List.of("A", "B", "C", "D", "E", "F"),
                    "DESC",
                    List.of(
                        new Task5.Contact("F"),
                        new Task5.Contact("E"),
                        new Task5.Contact("D"),
                        new Task5.Contact("C"),
                        new Task5.Contact("B"),
                        new Task5.Contact("A")
                    )
                ),
                Arguments.of(List.of(), "DESC", List.of()),
                Arguments.of(null, "DESC", List.of())
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ContactsArgumentProvider.class)
    public void shouldSortContactsGivenDifferentInputs(
        List<String> names,
        String type,
        List<Task5.Contact> expectedOutput
    ) {
        List<Task5.Contact> actualOutput = parseContactsWithStreams(names, type);

        assertThat(actualOutput).usingRecursiveComparison().isEqualTo(expectedOutput);
    }

}
