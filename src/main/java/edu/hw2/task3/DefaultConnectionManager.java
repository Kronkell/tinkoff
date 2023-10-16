package edu.hw2.task3;

import org.apache.logging.log4j.Logger;

public class DefaultConnectionManager implements ConnectionManager {
    private final Logger LOGGER;

    public DefaultConnectionManager(Logger logger) {
        LOGGER = logger;
    }

    @Override
    public Connection getConnection(int seed) {
        if (seed % 2 == 0) {
            return new FaultyConnection(LOGGER);
        } else {
            return new StableConnection(LOGGER);
        }
    }
}
