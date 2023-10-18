package edu.project1;

import org.jetbrains.annotations.NotNull;


public record Defeat(char[] state, int attempt, int maxAttempts) implements GuessResult {

    @Override
    public char[] state() {
        return state;
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
        return String.format("Missed, mistake %d out of %d.\nThe word was: %s\nYou lost!💀\n",
            attempt(), maxAttempts(), new String(state()));
    }

    @Override
    public @NotNull String additionalMessage() {
        return String.format("The word was: %s\nYou lost!💀", new String(state()));
    }
}
