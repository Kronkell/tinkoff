package edu.project1;

import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("MemberName")
public class FiveLetterWordsDictionary implements Dictionary {
    private final int NUMBER_OF_WORDS = 6;
    private final List<String> words = List.of("judge", "alien", "angle", "broom", "cacao", "error");

    @Override
    public @NotNull String randomWord() {
        int index = new Random().nextInt(NUMBER_OF_WORDS);
        return words.get(index);
    }
}
