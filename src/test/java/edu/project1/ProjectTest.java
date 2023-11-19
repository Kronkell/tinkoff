package edu.project1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProjectTest {
    ConsoleHangman game = new ConsoleHangman();

    @ParameterizedTest
    @CsvSource({"''", "KEKW", "1", "абоба", "D"})
    public void shouldAskAgainGivenIncorrectInput(String input) {
        var session = new Session(null, 0, null);
        GuessResult actualResult = game.tryGuess(session, input);

        assertThat(actualResult.message()).isEqualTo("Wrong input! Try again.");
    }

    @ParameterizedTest
    @CsvSource({"concede"})
    public void shouldEndTheGameGivenCommand(String input) {
        var session = new Session("answer", 0, null);
        GuessResult actualResult = game.tryGuess(session, input);

        assertThat(actualResult.additionalMessage()).isEqualTo("The word was: answer\n" +
            "You lost!\uD83D\uDC80");
    }

    @Test
    public void shouldEndTheGameGivenMoreThanMaxFailedAttempts() {
        var input = "b";
        var session  = new Session("answer", 1, "".toCharArray());
        GuessResult actualResult = game.tryGuess(session, input);

        assertThat(actualResult.message())
            .isEqualTo("Missed, mistake 1 out of 1.\nThe word was: answer\nYou lost!💀\n");
    }

    @Test
    public void shouldReturnNextStateGivenRightGuess() {
       var input = "a";
       var session = new Session("answer", 1, "*****".toCharArray());
       GuessResult actualResult = game.tryGuess(session, input);

        assertThat(actualResult.message())
            .isEqualTo("Hit!\nThe word: a****\n");
    }

    @Test
    public void shouldReturnNextStateGivenFailedGuess() {
        var input = "b";
        var session = new Session("answer", 2, "*****".toCharArray());
        GuessResult actualResult = game.tryGuess(session, input);

        assertThat(actualResult.message())
            .isEqualTo("Missed, mistake 1 out of 2.\nThe word: *****\n");
    }

    @Test
    public void shouldGiveHintGivenCommand() {
        var input = "hint";
        var session = new Session("answer", 2, "*****".toCharArray());
        GuessResult actualResult = game.tryGuess(session, input);

        assertThat(actualResult.message())
            .isEqualTo("Hit!\nThe word: a****\n");
    }

    @Test
    public void shouldReturnWinGivenRightWord() {
        var input = "a";
        var session = new Session("a", 2, "*".toCharArray());
        GuessResult actualResult = game.tryGuess(session, input);

        assertThat(actualResult.message())
            .isEqualTo("The word: a\nYou won!\n");
    }

}
