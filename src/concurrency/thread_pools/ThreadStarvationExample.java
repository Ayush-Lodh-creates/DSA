package concurrency.thread_pools;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadStarvationExample {

    public static AtomicInteger[] completedTasks = new AtomicInteger[3];

    static {
        for (int i=0; i< completedTasks.length; i++) {
            completedTasks[i] = new AtomicInteger(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Example 1 : Without Thread Pool - Potential Starvation ---");
        withoutThreadPool();

        for(AtomicInteger i : completedTasks) {
            i.set(0);
        }
        System.out.println("--- Example 2 : With Thread Pool - No Starvation ---");
        withThreadPool();
    }

    public static void withoutThreadPool() throws InterruptedException {
        final Object sharedResource = new Object();

        for(int i=0; i<30; i++) {
            Thread thread = new Thread(new PriorityTask(i%3, sharedResource));
            thread.setPriority(Thread.MIN_PRIORITY + (i%3) * 2);
            thread.start();
        }

        Thread.sleep(5000);
        System.out.println("Tasks completed by priority.");
        System.out.println("Low Priority " + completedTasks[0].get());
        System.out.println("Medium Priority " + completedTasks[1].get());
        System.out.println("High Priority " + completedTasks[2].get());
    }

    public static void withThreadPool() throws InterruptedException {
        final Object sharedResource = new Object();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        executor.setThreadFactory(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        for(int i=0; i<30; i++) {
            executor.submit(new PriorityTask(i%3, sharedResource));
        }

        Thread.sleep(5000);
        System.out.println("Tasks completed by priority.");
        System.out.println("Low Priority " + completedTasks[0].get());
        System.out.println("Medium Priority " + completedTasks[1].get());
        System.out.println("High Priority " + completedTasks[2].get());

        executor.shutdown();
    }
    static class PriorityTask implements Runnable {

        private final int priority;
        private final Object sharedResource;

        PriorityTask(int priority, Object sharedResource) {
            this.priority = priority;
            this.sharedResource = sharedResource;
        }

        @Override
        public void run() {
            try {
                for(int i=0; i<10; i++) {
                    synchronized (sharedResource) {
                        Thread.sleep(20 + (10 * priority));
                        completedTasks[priority].incrementAndGet();
                    }
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
