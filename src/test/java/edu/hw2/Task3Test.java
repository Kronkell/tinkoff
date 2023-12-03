package edu.hw2;

import java.util.ArrayList;
import java.util.List;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    private static MockedAppender mockedAppender;
    private static Logger logger;

    public static void setupClass() {
        mockedAppender = new MockedAppender();
        logger = (Logger) LogManager.getLogger(PopularCommandExecutor.class);
        logger.addAppender(mockedAppender);
        logger.setLevel(Level.INFO);
    }

    public static void teardown() {
        logger.removeAppender(mockedAppender);
    }

    private static class MockedAppender extends AbstractAppender {
        List<String> message = new ArrayList<>();

        protected MockedAppender() {
            super("MockedAppender", null, null);
        }

        @Override
        public void append(LogEvent event) {
            message.add(event.getMessage().getFormattedMessage());
        }
    }

    @Test
    public void shouldExecuteGivenDefaultConnectionManager() {
        setupClass();
        PopularCommandExecutor executor = new PopularCommandExecutor(new DefaultConnectionManager(logger), 5);
        executor.updatePackages();

        assertThat("Closing FaultyConnection...")
            .isEqualTo(mockedAppender.message.get(0));
        assertThat("Caught ConnectionException: ConnectionException: Execution failed!")
            .isEqualTo(mockedAppender.message.get(1));
        assertThat("Executing apt update && apt upgrade -y...")
            .isEqualTo(mockedAppender.message.get(2));
        assertThat("Closing StableConnection...")
            .isEqualTo(mockedAppender.message.get(3));
        logger.removeAppender(mockedAppender);
        teardown();
    }

    @Test
    public void shouldExecuteGivenFaultyConnectionManager() {
        setupClass();
        PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager(logger), 5);
        executor.updatePackages();

        assertThat("Closing FaultyConnection...")
            .isEqualTo(mockedAppender.message.get(0));
        assertThat("Caught ConnectionException: ConnectionException: Execution failed!")
            .isEqualTo(mockedAppender.message.get(1));
        assertThat("Executing apt update && apt upgrade -y...")
            .isEqualTo(mockedAppender.message.get(2));
        assertThat("Closing FaultyConnection...")
            .isEqualTo(mockedAppender.message.get(3));
        logger.removeAppender(mockedAppender);
        teardown();
    }

}
