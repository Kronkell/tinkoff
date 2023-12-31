package edu.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MemberName")
public class ConsoleHangman {
    private final Logger LOGGER = LogManager.getLogger();
    private final int FIRST = 0;
    private final int MAX_ATTEMPTS = 5;
    private final char[] DEFAULT_STATE = "*****".toCharArray();
    private final String HINT = "hint";
    private final String CONCEDE = "concede";
    private final List<String> consoleCommands = List.of(CONCEDE, HINT);
    private boolean isFinished = false;

    public void run() {
        Dictionary dictionary = new FiveLetterWordsDictionary();
        ConsoleTemplates templates = new ConsoleTemplates(LOGGER);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            templates.printHangmanLogo();
            templates.printRules();
            templates.printCommandsInfo();
            while (!isFinished) {
                String word = dictionary.randomWord();
                Session currentSession = new Session(word, MAX_ATTEMPTS, DEFAULT_STATE);
                while (!currentSession.isFinished()) {
                    LOGGER.info("Guess a letter:");
                    String input = bufferedReader.readLine();
                    GuessResult result = tryGuess(currentSession, input);
                    if (result == null) {
                        continue;
                    }
                    printState(result);
                }
                templates.askForAnotherGame();
                String ans = bufferedReader.readLine();
                isFinished = !Objects.equals(ans, "Y");
            }
        } catch (IOException e) {
            LOGGER.info(Arrays.toString(e.getStackTrace()));
        }
    }

    protected GuessResult tryGuess(Session session, String input) {
        GuessResult guessResult;
        if (input == null) {
            guessResult = executeConsoleCommand(session, consoleCommands.get(0));
        } else if (consoleCommands.contains(input)) {
            guessResult = executeConsoleCommand(session, input);
        } else if (input.length() == 1 && input.charAt(FIRST) >= 'a' && input.charAt(FIRST) <= 'z') {
            guessResult = session.guess(input.charAt(FIRST));
        } else {
            guessResult = new WrongInput();
        }

        return guessResult;
    }

    private GuessResult executeConsoleCommand(Session session, String command) {
        if (Objects.equals(command, HINT)) {
            return session.giveHint();
        } else {
            return session.giveUp();
        }
    }

    @SuppressWarnings("MagicNumber")
    private void printState(GuessResult guess) {
        switch (guess) {
            case SuccessfulGuess ignored -> LOGGER.info(guess.message());
            case FailedGuess ignored -> {
                switch (guess.attempt()) {
                    case 1:
                        printHangStages(0);
                        LOGGER.info(guess.message());
                        break;
                    case 2:
                        printHangStages(1);
                        LOGGER.warn(guess.message());
                        break;
                    case 3:
                        printHangStages(2);
                        LOGGER.error(guess.message());
                        break;
                    case 4:
                        printHangStages(3);
                        LOGGER.fatal(guess.message());
                        break;
                    case 5:
                        LOGGER.trace(guess.message());
                        break;
                    default:
                        break;
                }
            }
            case Win ignored -> LOGGER.debug(guess.message());
            case Defeat ignored -> {
                printHangStages(4);
                LOGGER.trace(guess.additionalMessage());
            }
            case WrongInput ignored -> LOGGER.fatal(guess.message());
            default -> throw new IllegalStateException("Unexpected value: " + guess);
        }
    }

    @SuppressWarnings("MagicNumber")
    public void printHangStages(int stageIndex) {
        String[] stages = {
            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",

            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",

            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",

            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",

            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="};

        switch (stageIndex) {
            case 0:
                LOGGER.info(stages[stageIndex]);
                break;
            case 1:
                LOGGER.warn(stages[stageIndex]);
                break;
            case 2:
                LOGGER.error(stages[stageIndex]);
                break;
            case 3:
                LOGGER.fatal(stages[stageIndex]);
                break;
            case 4:
                LOGGER.trace(stages[stageIndex]);
                break;
            default:
                break;

        }
    }
}
