package concurrency.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DiningPhilosopher {

    private Semaphore semaphore;
    private Semaphore[] forkSemaphore;

    public DiningPhilosopher() {
        this.semaphore = new Semaphore(4);
        this.forkSemaphore = new Semaphore[5];
        for(int i=0; i<5; i++) {
            forkSemaphore[i] = new Semaphore(1);
        }
    }

    public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat, Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {
        semaphore.acquire();

        int left = philosopher;
        int right = (philosopher + 1) % 5;

        Semaphore leftFork = forkSemaphore[left];
        Semaphore rightFork = forkSemaphore[right];

        leftFork.acquire();
        rightFork.acquire();

        pickLeftFork.run();
        pickRightFork.run();
        eat.run();

        putLeftFork.run();
        leftFork.release();
        putRightFork.run();
        rightFork.release();

        semaphore.release();
    }

    public static void main(String[] args) throws InterruptedException {
        DiningPhilosopher diningPhilosopher = new DiningPhilosopher();

        ExecutorService philosophers = Executors.newFixedThreadPool(5);

        for(int i=0; i<10; i++) {
            int philosopher = i % 5;
            philosophers.submit(() -> {
                try {
                    diningPhilosopher.wantsToEat(philosopher,
                            () -> System.out.println("Philosopher " + philosopher + " picked up left fork"),
                            () -> System.out.println("Philosopher " + philosopher + " picked up right fork"),
                            () -> System.out.println("Philosopher " + philosopher + " is eating"),
                            () -> System.out.println("Philosopher " + philosopher + " put down left fork"),
                            () -> System.out.println("Philosopher " + philosopher + " put down right fork"));
                } catch (InterruptedException e) {
                    System.out.println("Something went wrong while execution");
                }
            });
        }

        philosophers.shutdown();
        Boolean philosophersDone = philosophers.awaitTermination(10, TimeUnit.SECONDS);
        if(philosophersDone) {
            System.out.println("All philosophers have finished eating");
        } else {
            System.out.println("Unexpected error");
        }
    }
}
