package concurrency.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLock {

    private int counter = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock");
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented counter to : " + counter);
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the lock");
            lock.unlock();
        }
    }
     public int getCounter() {
        return counter;
     }

     public static void main(String[] args) {
        ReEntrantLock reEntrantLock = new ReEntrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0; i<5; i++) {
            executorService.submit(reEntrantLock::increment);
        }
        executorService.shutdown();
        try {
            if(executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Final Counter value " + reEntrantLock.getCounter());
            } else {
                System.out.println("Timeout : Not all finished tasks");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted while finishing tasks");
            Thread.currentThread().interrupt();
        }
     }
}
