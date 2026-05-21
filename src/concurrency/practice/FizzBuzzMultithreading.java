package concurrency.practice;

import java.util.concurrent.Semaphore;

public class FizzBuzzMultithreading {

    private int n;
    private Semaphore numberSemaphore;
    private Semaphore fizzSemaphore;
    private Semaphore buzzSemaphore;
    private Semaphore fizzBuzzSemaphore;

    public FizzBuzzMultithreading(int n) {
        this.n = n;
        numberSemaphore = new Semaphore(1);
        fizzSemaphore = new Semaphore(0);
        buzzSemaphore = new Semaphore(0);
        fizzBuzzSemaphore = new Semaphore(0);
    }

    public void fizz() throws InterruptedException {
        for(int i=3; i<=n; i=i+3) {
            if(i%5 != 0) {
                fizzSemaphore.acquire();
                System.out.println("Fizz");
                numberSemaphore.release();
            }
        }
    }

    public void buzz() throws InterruptedException {
        for(int i=5; i<=n; i=i+5) {
            if(i%3 != 0) {
                buzzSemaphore.acquire();
                System.out.println("Buzz");
                numberSemaphore.release();
            }
        }
    }

    public void fizzBuzz() throws InterruptedException {
        for(int i=15; i<=n; i=i+15) {
            fizzBuzzSemaphore.acquire();
            System.out.println("FizzBuzz");
            numberSemaphore.release();
        }
    }

    public void number() throws InterruptedException {
        for(int i=1; i<=n; i++) {
            numberSemaphore.acquire();
            if(i%3 == 0 && i%5 != 0) {
                fizzSemaphore.release();
            } else if(i%5 == 0 && i%3 != 0) {
                buzzSemaphore.release();
            } else if(i%3 == 0) {
                fizzBuzzSemaphore.release();
            } else {
                System.out.println(i);
                numberSemaphore.release();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FizzBuzzMultithreading fizzBuzzMultithreading = new FizzBuzzMultithreading(33);
        Thread t1 = new Thread(() -> {
            try {
                fizzBuzzMultithreading.fizz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                fizzBuzzMultithreading.buzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                fizzBuzzMultithreading.fizzBuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                fizzBuzzMultithreading.number();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Execution start");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Execution end");
    }
}
