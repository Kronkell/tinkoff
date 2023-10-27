package edu.hw2.task3;

import org.apache.logging.log4j.Logger;

@SuppressWarnings("MemberName")
public class DefaultConnectionManager implements ConnectionManager {
    private final Logger LOGGER;
    private final int THREE = 3;

    public DefaultConnectionManager(Logger logger) {
        LOGGER = logger;
    }

    @Override
    public Connection getConnection(int seed) {
        if (seed % THREE == 0) {
            return new FaultyConnection(LOGGER, seed);
        } else {
            return new StableConnection(LOGGER);
        }
    }
}
