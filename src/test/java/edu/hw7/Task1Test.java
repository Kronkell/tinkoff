package edu.hw7;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    public void testThreadSafety() {
        final int expected = 100;
        Task1 task1 = new Task1();

        task1.increment(expected);

        assertThat(task1.counter.get()).isEqualTo(expected);
    }

    @Test
    public void testThreadSafetyOneThread() {
        final int expected = 1;
        Task1 task1 = new Task1();

        task1.increment(expected);

        assertThat(task1.counter.get()).isEqualTo(expected);
    }
}
