package edu.hw8;

import java.util.concurrent.CompletableFuture;
import edu.hw8.Task2.FixedThreadPool;
import edu.hw8.Task2.ThreadPool;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    public static class Fibonacci implements Runnable {
        int[] arr;
        int N;
        CompletableFuture<Integer> compFuture;

        public Fibonacci(int N, CompletableFuture<Integer> compFuture) {
            this.N = N;
            this.compFuture = compFuture;
            arr = new int[N];
            arr[0] = 1;
            arr[1] = 1;
        }

        @Override
        public void run() {
            for (int i = 2; i < N; ++i) {
                arr[i] = arr[i - 1] + arr[i - 2];
            }

            compFuture.complete(arr[N - 1]);
        }
    }

    @Test
    public void calculateFibonacciNumbersWithThreadPoolLessThreads() throws Exception {
        int nThreads = 2;
        int expectedResult1 = 55;
        int expectedResult2 = 6765;
        int expectedResult3 = 832040;

        CompletableFuture<Integer> compFuture1 = new CompletableFuture<>();
        CompletableFuture<Integer> compFuture2 = new CompletableFuture<>();
        CompletableFuture<Integer> compFuture3 = new CompletableFuture<>();
        try (ThreadPool threadPool = FixedThreadPool.create(nThreads)) {
            threadPool.start();
            threadPool.execute(new Fibonacci(10, compFuture1));
            threadPool.execute(new Fibonacci(20, compFuture2));
            threadPool.execute(new Fibonacci(30, compFuture3));
        }

        assertThat(compFuture1.get()).isEqualTo(expectedResult1);
        assertThat(compFuture2.get()).isEqualTo(expectedResult2);
        assertThat(compFuture3.get()).isEqualTo(expectedResult3);
    }

    @Test
    public void calculateFibonacciNumbersWithThreadPoolMoreThreads() throws Exception {
        int nThreads = 10;
        int expectedResult1 = 55;
        int expectedResult2 = 6765;
        int expectedResult3 = 832040;

        CompletableFuture<Integer> compFuture1 = new CompletableFuture<>();
        CompletableFuture<Integer> compFuture2 = new CompletableFuture<>();
        CompletableFuture<Integer> compFuture3 = new CompletableFuture<>();
        try (ThreadPool threadPool = FixedThreadPool.create(nThreads)) {
            threadPool.start();
            threadPool.execute(new Fibonacci(10, compFuture1));
            threadPool.execute(new Fibonacci(20, compFuture2));
            threadPool.execute(new Fibonacci(30, compFuture3));
        }

        assertThat(compFuture1.get()).isEqualTo(expectedResult1);
        assertThat(compFuture2.get()).isEqualTo(expectedResult2);
        assertThat(compFuture3.get()).isEqualTo(expectedResult3);
    }

    @Test
    public void calculateFibonacciNumbersWithThreadPoolSameThreads() throws Exception {
        int nThreads = 3;
        int expectedResult1 = 55;
        int expectedResult2 = 6765;
        int expectedResult3 = 832040;

        CompletableFuture<Integer> compFuture1 = new CompletableFuture<>();
        CompletableFuture<Integer> compFuture2 = new CompletableFuture<>();
        CompletableFuture<Integer> compFuture3 = new CompletableFuture<>();
        try (ThreadPool threadPool = FixedThreadPool.create(nThreads)) {
            threadPool.start();
            threadPool.execute(new Fibonacci(10, compFuture1));
            threadPool.execute(new Fibonacci(20, compFuture2));
            threadPool.execute(new Fibonacci(30, compFuture3));
        }

        assertThat(compFuture1.get()).isEqualTo(expectedResult1);
        assertThat(compFuture2.get()).isEqualTo(expectedResult2);
        assertThat(compFuture3.get()).isEqualTo(expectedResult3);
    }
}
