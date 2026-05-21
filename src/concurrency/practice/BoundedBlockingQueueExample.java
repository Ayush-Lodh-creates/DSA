package concurrency.practice;

import java.sql.Time;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BoundedBlockingQueueExample {

    private int size;
    private Semaphore producers;
    private Semaphore consumers;
    private ConcurrentLinkedQueue<Integer> queue;
    private AtomicInteger counter;

    public BoundedBlockingQueueExample(int n) {
        this.size = n;
        producers = new Semaphore(n);
        consumers = new Semaphore(0);
        queue = new ConcurrentLinkedQueue<>();
        counter = new AtomicInteger(1);
    }

    public void produce() throws InterruptedException {
        producers.acquire();
        int val = counter.getAndIncrement();
        System.out.println("Produced a value : " + val);
        queue.add(val);
        consumers.release();
    }

    public void consumers() throws InterruptedException {
        consumers.acquire();
        System.out.println("Consumed a value : " + queue.poll());
        producers.release();
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedBlockingQueueExample boundedBlockingQueueExample = new BoundedBlockingQueueExample(5);
        ExecutorService producers = Executors.newFixedThreadPool(2);
        ExecutorService consumers = Executors.newFixedThreadPool(1);

        for(int i=1; i<=10; i++) {
            producers.submit(() -> {
                try {
                    boundedBlockingQueueExample.produce();
                } catch (InterruptedException e) {
                    System.out.println("Producer interrupted");
                    throw new RuntimeException(e);
                }
            });
            consumers.submit(() -> {
                try {
                    boundedBlockingQueueExample.consumers();
                } catch (InterruptedException e) {
                    System.out.println("Consumer interrupted");
                    throw new RuntimeException(e);
                }
            });
        }

        producers.shutdown();
        consumers.shutdown();

        boolean producersDone = producers.awaitTermination(10, TimeUnit.SECONDS);
        boolean consumersDone = consumers.awaitTermination(10, TimeUnit.SECONDS);

        if(producersDone && consumersDone) {
            System.out.println("Execution completed");
        } else {
            System.out.println("Timeout exception occured");
        }
    }
}
