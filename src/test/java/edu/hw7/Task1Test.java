package edu.hw7;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    public void testThreadSafety() {
        int N = 100;
        Stream.generate(() -> new Thread(new Task1.Worker()))
            .limit(N)
            .forEach(x -> {
                x.start();
                try {
                    x.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

        assertThat(Task1.counter.get()).isEqualTo(N);
    }
}
