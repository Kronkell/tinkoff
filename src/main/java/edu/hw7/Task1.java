package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Task1 {
    AtomicInteger counter = new AtomicInteger();

    public void increment(int n) {
        Stream.generate(() -> new Thread(new Worker()))
            .limit(n)
            .forEach(x -> {
                x.start();
                try {
                    x.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    public class Worker implements Runnable {

        @Override
        public void run() {
            counter.incrementAndGet();
        }
    }
}
