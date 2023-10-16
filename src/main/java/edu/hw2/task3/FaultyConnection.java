package edu.hw2.task3;

import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final Logger LOGGER;

    public FaultyConnection(Logger logger) {
        LOGGER = logger;
    }
    @Override
    public void execute(String command, int seed) {
        if (seed % 2 == 0) {
            throw new ConnectionException("Execution failed!");
        }
        else {
            LOGGER.info("Executing " + this.getClass().getSimpleName() + "...");
        }
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Closing " + this.getClass().getSimpleName() + "...");
    }
}
