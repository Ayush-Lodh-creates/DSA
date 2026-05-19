package concurrency.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReEntrantReadWriteLock {

    private int logValue = 0;
    private final ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    private void writeValue(String taskName, int value) {
        rwlock.writeLock().lock();
        try {
            System.out.println(taskName + " acquired the lock");
            Thread.sleep(5000);
            logValue = value;
            System.out.println(taskName + " updated value to : " + logValue);
        } catch (InterruptedException e) {
            System.out.println(taskName + " was interrupted while writing value");
            System.out.println(e.getMessage());
        } finally {
            System.out.println(taskName + " released the lock");
            rwlock.writeLock().unlock();
        }
    }

    private void readValue(String taskName) {
        rwlock.readLock().lock();
        try {
            System.out.println(taskName + " acquired the lock");
            Thread.sleep(5000);
            System.out.println("The log value is : " + logValue);
        } catch (InterruptedException e) {
            System.out.println(taskName + " was interrupted while writing value");
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println(taskName + " released the lock");
            rwlock.readLock().unlock();
        }
    }

    private int getLogValue() {
        return logValue;
    }

    public static void main(String[] args) throws InterruptedException {
        ReEntrantReadWriteLock reEntrantReadWriteLock = new ReEntrantReadWriteLock();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(() -> {
            reEntrantReadWriteLock.readValue("Reader-1");
        });
        executorService.submit(() -> {
            reEntrantReadWriteLock.readValue("Reader-2");
        });
        executorService.submit(() -> {
            reEntrantReadWriteLock.writeValue("Writer-1", 100);
        });
        Thread.sleep(15000);
        executorService.submit(() -> {
            reEntrantReadWriteLock.readValue("Reader-3");
        });
        executorService.submit(() -> {
            reEntrantReadWriteLock.readValue("Reader-4");
        });
        executorService.submit(() -> {
            reEntrantReadWriteLock.writeValue("Writer-2", 200);
        });
        Thread.sleep(15000);
        executorService.submit(() -> {
            reEntrantReadWriteLock.readValue("Reader-5");
        });
        executorService.shutdown();
        try {
            if(executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                System.out.println("Final Counter value " + reEntrantReadWriteLock.getLogValue());
            } else {
                System.out.println("Timeout : Not all finished tasks");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted while finishing tasks");
            Thread.currentThread().interrupt();
        }
    }
}
