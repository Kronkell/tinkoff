package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Task0 {
    private Task0() {
    }

    public static final Logger LOGGER = LogManager.getLogger();

    public static void loggerInfo() {
        LOGGER.info("Привет, мир!");
    }
}
