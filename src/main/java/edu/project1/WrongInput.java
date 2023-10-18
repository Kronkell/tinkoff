package edu.project1;

import org.jetbrains.annotations.NotNull;

public record WrongInput() implements GuessResult {
    @Override
    public char[] state() {
        return new char[0];
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
        return "Wrong input! Try again.";
    }

    @Override
    public @NotNull String additionalMessage() {
        return null;
    }
}
