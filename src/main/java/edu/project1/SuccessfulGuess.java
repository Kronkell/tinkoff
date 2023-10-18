package edu.project1;

import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public record SuccessfulGuess(char[] oldState, char newLetter, List<Integer> indices) implements GuessResult {
    @Override
    public char[] state() {
        char[] newState = Arrays.copyOf(oldState, oldState.length);
        for(Integer letter : indices) {
            newState[letter] = newLetter;
        }
        return newState;
    }

    @Override
    public int attempt() {
        return 0;
    }

    @Override
    public int maxAttempts() {
        return 0;
    }

    @Override
    public @NotNull String message() {
        return String.format("Hit!\nThe word: %s\n", new String(state()));
    }

    @Override
    public @NotNull String additionalMessage() {
        return "Go on!";
    }
}
