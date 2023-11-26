package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    private Task1() {
    }

    static AtomicInteger counter = new AtomicInteger();

    public static class Worker implements Runnable {

        @Override
        public void run() {
            counter.incrementAndGet();
        }
    }
}
