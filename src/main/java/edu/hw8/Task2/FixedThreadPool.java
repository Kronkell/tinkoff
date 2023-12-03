package edu.hw8.Task2;

import java.util.concurrent.LinkedBlockingQueue;

@SuppressWarnings("RegexpSinglelineJava")
public class FixedThreadPool implements ThreadPool {
    private final int nThreads;
    private final Thread[] threads;
    private final LinkedBlockingQueue<Runnable> queue;

    private FixedThreadPool(int nThreads) {
        this.nThreads = nThreads;
        queue = new LinkedBlockingQueue<>();
        threads = new Thread[nThreads];

        for (int i = 0; i < nThreads; ++i) {
            threads[i] = new Worker("FixedThreadPool thread " + i);
        }
    }

    public static FixedThreadPool create(int nThreads) {
        return new FixedThreadPool(nThreads);
    }

    @Override
    public void start() {
        for (int i = 0; i < nThreads; ++i) {
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            queue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stop() {
        for (int i = 0; i < nThreads; ++i) {
            threads[i].interrupt();
        }
    }

    public synchronized void waitUntilAllTasksFinished() {
        while (!this.queue.isEmpty()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() {
        waitUntilAllTasksFinished();
        stop();
    }

    class Worker extends Thread {
        Worker(String name) {
            super(name);
        }

        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    synchronized (queue) {
                        if (!queue.isEmpty()) {
                            Runnable task = queue.take();
                            task.run();
                        }
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
