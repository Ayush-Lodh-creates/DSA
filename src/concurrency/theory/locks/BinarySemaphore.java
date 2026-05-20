package concurrency.theory.locks;

import java.util.concurrent.Semaphore;

public class BinarySemaphore {

    private static final Semaphore mutex = new Semaphore(1);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> accessCriticalSection("Thread-1"));
        Thread t2 = new Thread(() -> accessCriticalSection("Thread-2"));

        t1.start();
        t2.start();
    }

    public static void accessCriticalSection(String threadName) {
        try {
            System.out.println(threadName + " is trying to acquire the semaphore...");
            mutex.acquire();
            System.out.println(threadName + " has acquired the semaphore.");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(threadName + " was interrupted while waiting for the semaphore.");
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(threadName + " is releasing the semaphore.");
            mutex.release();
        }
    }
}
