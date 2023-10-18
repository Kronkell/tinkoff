package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("MemberName")
public class Session {
    private final int MAX_SIZE = 1000;
    private final char ASTERISK = '*';
    private final String answer;
    private final int maxAttempts;
    private char[] userAnswer = new char[MAX_SIZE];
    private int attempts = 0;
    private int failedAttempts = 0;

    private char[] currentState;

    private boolean isFinished;

    public Session(String answer, int maxAttempts, char[] currentState) {
        this.answer = answer;
        this.maxAttempts = maxAttempts;
        this.currentState = currentState;
    }

    @NotNull GuessResult guess(char guess) {
        GuessResult result;
        attempts++;
        userAnswer[attempts] = guess;
        if (isCharInAnswer(guess)) {
            List<Integer> indices = new ArrayList<>();
            for (int i = 0; i < answer.length(); ++i) {
                if (answer.charAt(i) == guess) {
                    indices.add(i);
                }
            }

            Supplier<Stream<Character>> charStreamSupplier =
                () -> new String(getCurrentState()).chars().mapToObj(i -> (char) i);

            if (charStreamSupplier.get().noneMatch(i -> i == guess)
                && charStreamSupplier.get().filter(i -> i == ASTERISK).count() == indices.size()) {
                isFinished = true;
                result = new Win(currentState, guess, indices);
            } else {
                result = new SuccessfulGuess(currentState, guess, indices);
            }
        } else {
            failedAttempts++;

            if (failedAttempts >= maxAttempts) {
                isFinished = true;
                result = new Defeat(answer.toCharArray(), failedAttempts, maxAttempts);
            } else {
                result = new FailedGuess(currentState, failedAttempts, maxAttempts);
            }
        }

        currentState = result.state();

        return result;
    }

    @NotNull GuessResult giveUp() {
        isFinished = true;
        return new Defeat(answer.toCharArray(), maxAttempts, maxAttempts);
    }

    @NotNull GuessResult giveHint() {
        int i;
        char openedLetter = '0';
        char[] curState = currentState;
        for (i = 0; i < curState.length; ++i) {
            if (curState[i] == ASTERISK) {
                openedLetter = answer.charAt(i);
                break;
            }
        }

        return this.guess(openedLetter);
    }

    boolean isCharInAnswer(char guess) {
        return answer.contains(String.valueOf(guess));
    }

    public char[] getCurrentState() {
        return currentState;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
