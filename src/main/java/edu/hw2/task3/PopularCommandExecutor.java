package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"MemberName", "MultipleStringLiterals"})
public final class PopularCommandExecutor {
    private final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        int countOfAttempts = 0;

        while (countOfAttempts < maxAttempts) {
            try (Connection connection = manager.getConnection(countOfAttempts)) {
                countOfAttempts++;
                connection.execute(command);
                break;
            } catch (ConnectionException e) {
                LOGGER.info("Caught ConnectionException: " + e.getMessage());
                if (countOfAttempts == maxAttempts) {
                    throw new ConnectionException("Reached maximum number of attempts!", e);
                }
            } catch (Exception exp) {
                LOGGER.info("Caught exception: " + exp.getMessage());
            }
        }
    }
}
