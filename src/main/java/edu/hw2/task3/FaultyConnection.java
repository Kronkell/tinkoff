package edu.hw2.task3;

import org.apache.logging.log4j.Logger;

@SuppressWarnings("MemberName")
public class FaultyConnection implements Connection {
    private final Logger LOGGER;

    public FaultyConnection(Logger logger) {
        LOGGER = logger;
    }

    @SuppressWarnings("MultipleStringLiterals")
    @Override
    public void execute(String command, int seed) {
        if (seed % 2 == 1) {
            throw new ConnectionException("ConnectionException: Execution failed!");
        } else {
            LOGGER.info("Executing " + this.getClass().getSimpleName() + "...");
        }
    }

    @Override
    public void close() {
        LOGGER.info("Closing " + this.getClass().getSimpleName() + "...");
    }
}
