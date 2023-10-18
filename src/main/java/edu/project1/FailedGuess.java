package edu.project1;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public record FailedGuess(char[] oldState, int attempt, int maxAttempts) implements GuessResult {
    @Override
    public char[] state() {
        return oldState;
    }

    @Override
    public int attempt() {
        return attempt;
    }

    @Override
    public int maxAttempts() {
        return maxAttempts;
    }

    @Override
    public @NotNull String message() {
        return String.format("Missed, mistake %d out of %d.\nThe word: %s\n",
            attempt(), maxAttempts(), new String(state()));
    }

    @Override
    public @NotNull String additionalMessage() {
        return "Try better!";
    }
}
