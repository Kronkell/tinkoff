package edu.hw7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

@SuppressWarnings({"ParameterName", "MagicNumber"})
public class Task4 {
    private Task4() {
    }

    public static double singleThreadMonteCarlo(long n) {
        long totalCount = 0;
        long circleCount = 0;
        double x;
        double y;
        for (int i = 0; i < n; ++i) {
            x = ThreadLocalRandom.current().nextDouble();
            y = ThreadLocalRandom.current().nextDouble();
            if (x * x + y * y <= 1) {
                circleCount++;
            }
            totalCount++;
        }

        return 4 * ((double) circleCount / totalCount);
    }

    static LongAdder totalCountAll = new LongAdder();
    static LongAdder circleCountAll = new LongAdder();

    public static double multiThreadMonteCarlo(long N, int coresNumber) throws InterruptedException {
        final ExecutorService exec = Executors.newFixedThreadPool(coresNumber);

        for (int i = 0; i < coresNumber; ++i) {
            exec.execute(new Worker(N / coresNumber));
        }

        exec.shutdown();
        exec.awaitTermination(1, TimeUnit.MINUTES);

        return 4 * ((double) circleCountAll.longValue() / totalCountAll.longValue());
    }

    private static class Worker implements Runnable {
        private final long numberOfIterations;

        private Worker(long N) {
            numberOfIterations = N;
        }

        @Override
        public void run() {
            long totalCount = 0;
            long circleCount = 0;
            double x;
            double y;
            for (int i = 0; i < numberOfIterations; ++i) {
                x = ThreadLocalRandom.current().nextDouble();
                y = ThreadLocalRandom.current().nextDouble();
                if (x * x + y * y <= 1) {
                    circleCount++;
                }
                totalCount++;
            }
            totalCountAll.add(totalCount);
            circleCountAll.add(circleCount);
        }
    }
}
