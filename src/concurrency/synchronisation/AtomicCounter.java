package concurrency.synchronisation;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    private AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        int newVal = counter.incrementAndGet();
        System.out.println("Thread 1 " + Thread.currentThread().getName());
    }

    public int getCounter() {
        return counter.get();
    }

    public static void main(String[] args) {
        final AtomicCounter atomicCounter = new AtomicCounter();
        int numOfThreads = 10;
        Thread[] threads = new Thread[numOfThreads];
        for(int i=0; i<5; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    atomicCounter.increment();
                }
            }, "Thread-" + (i+1));
            threads[i].start();
        }
        for(int i=0; i<numOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Final counter value : " + atomicCounter.getCounter());
    }
}
