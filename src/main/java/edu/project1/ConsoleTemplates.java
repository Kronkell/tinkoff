package edu.project1;

import org.apache.logging.log4j.Logger;

@SuppressWarnings("MemberName")
public class ConsoleTemplates {
    private final Logger LOGGER;

    public ConsoleTemplates(Logger logger) {
        LOGGER = logger;
    }

    public void printRules() {
        String rules = "The word to guess is represented by a row of asterisks representing each letter of the word.\n"
            + "You should try to guess it by suggesting letters within a certain number of guesses. Good luck!";
        LOGGER.debug(rules);
    }

    public void printCommandsInfo() {
        String choice = """
            You have 2 console commands in your disposal - 'hint' and 'concede'.\s
            Their names speak for themselves ;)
            """;
        LOGGER.info(choice);
    }

    public void printDictChoice() {
        String choice = "Choose the number of letters in a word? \nPrint 1 if you want to guess 5-letter word, and "
            + "and print 2 if you want 6-letter word";
        LOGGER.info(choice);
    }

    public void askForAnotherGame() {
        String question = "Type 'Y' if you want to try it one more time. 'N' if not.";
        LOGGER.info(question);
    }

    public void printHangmanLogo() {
        String logo = """
             _                                            \s
            | |                                           \s
            | |__   __ _ _ __   __ _ _ __ ___   __ _ _ __ \s
            | '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\\s
            | | | | (_| | | | | (_| | | | | | | (_| | | | |
            |_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|
                                __/ |                     \s
                               |___/                      \s""";
        LOGGER.fatal(logo);
    }
}
