package concurrency.practice;

import java.util.concurrent.Semaphore;

public class PrintZeroOddEven {

    private int n;
    private Semaphore zeroSemaphore;
    private Semaphore oddSemaphore;
    private Semaphore evenSemaphore;

    public PrintZeroOddEven(int n) {
        this.n = n;
        this.zeroSemaphore = new Semaphore(1);
        this.oddSemaphore = new Semaphore(0);
        this.evenSemaphore = new Semaphore(0);
    }

    public void printZero() throws InterruptedException {
        boolean odd = true;
        for(int i=1; i<=n; i++) {
            zeroSemaphore.acquire();
            System.out.println(0);
            if(odd) {
                oddSemaphore.release();
            } else {
                evenSemaphore.release();
            }
            odd = !odd;
        }
    }

    public void printOdd() throws InterruptedException {
        for(int i=1; i<=n; i=i+2) {
            oddSemaphore.acquire();
            System.out.println(i);
            zeroSemaphore.release();
        }
    }

    public void printEven() throws InterruptedException {
        for(int i=2; i<=n; i=i+2) {
            evenSemaphore.acquire();
            System.out.println(i);
            zeroSemaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PrintZeroOddEven printZeroOddEven = new PrintZeroOddEven(5);

        Thread zeroThread = new Thread(() -> {
            try {
                printZeroOddEven.printZero();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread oddThread = new Thread(() -> {
            try {
                printZeroOddEven.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread evenThread = new Thread(() -> {
            try {
                printZeroOddEven.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Execution started");

        zeroThread.start();
        oddThread.start();
        evenThread.start();

        zeroThread.join();
        oddThread.join();
        evenThread.join();

        System.out.println("Execution completed");
    }
}
