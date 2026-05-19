package concurrency.locks;

import java.util.concurrent.Semaphore;

public class CountingSemaphore {

    private static final Semaphore mutex = new Semaphore(3);

    public static void main(String[] args) {

        for(int i=0; i<5; i++) {
            Thread t1 = new Thread(() -> accessCriticalSection(Thread.currentThread().getName()));
            t1.start();
        }
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
