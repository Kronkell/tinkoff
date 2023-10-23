package edu.hw2.task3;

import org.apache.logging.log4j.Logger;

@SuppressWarnings("MemberName")
public class FaultyConnection implements Connection {
    private final Logger LOGGER;

    private int seed;

    public FaultyConnection(Logger logger, int seed) {
        LOGGER = logger;
        this.seed = seed;
    }

    @SuppressWarnings("MultipleStringLiterals")
    @Override
    public void execute(String command) {
        if (seed % 2 == 0) {
            throw new ConnectionException("ConnectionException: Execution failed!");
        } else {
            LOGGER.info("Executing " + command + "...");
        }
    }

    @Override
    public void close() {
        LOGGER.info("Closing " + this.getClass().getSimpleName() + "...");
    }
}
