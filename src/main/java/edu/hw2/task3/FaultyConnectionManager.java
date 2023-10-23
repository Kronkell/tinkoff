package edu.hw2.task3;

import org.apache.logging.log4j.Logger;

@SuppressWarnings("MemberName")
public class FaultyConnectionManager implements ConnectionManager {
    private final Logger LOGGER;

    public FaultyConnectionManager(Logger logger) {
        LOGGER = logger;
    }

    @Override
    public Connection getConnection(int seed) {
        return new FaultyConnection(LOGGER, seed);
    }
}
