package edu.hw2.task3;

import org.apache.logging.log4j.Logger;

@SuppressWarnings({"MemberName", "MultipleStringLiterals"})
public class StableConnection implements Connection {
    private final Logger LOGGER;

    public StableConnection(Logger logger) {
        LOGGER = logger;
    }

    @Override
    public void execute(String command, int seed) {
        LOGGER.info("Executing " + command + "...");
    }

    @Override
    public void close() {
        LOGGER.info("Closing " + this.getClass().getSimpleName() + "...");
    }
}
