package concurrency.locks;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueExample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Example 1: LinkedList with Collections.synchronizedList (Blocking Producer-Consumer) ---");
        demonstrateSynchronizedQueue();

        System.out.println("\n--- Example 2: ConcurrentLinkedQueue (Safe Concurrent Producer-Consumer) ---");
        demonstrateConcurrentLinkedQueue();

        System.out.println("\n--- Example 3: Showing Iteration Safety During Operations ---");
        demonstrateIterationSafety();
    }

    // Example 1: Collections.synchronizedList with LinkedList - Requires external sync for iteration
    public static void demonstrateSynchronizedQueue() throws InterruptedException {
        final List<Integer> synchronizedQueue = Collections.synchronizedList(new LinkedList<>());

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                synchronizedQueue.add(i);
                System.out.println("Produced: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(50); // Wait for some items to be produced
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            
            // Need to synchronize externally to avoid ConcurrentModificationException
            synchronized (synchronizedQueue) {
                System.out.println("Synchronized Queue Contents: " + synchronizedQueue);
                for (Integer num : synchronizedQueue) {
                    System.out.println("  Consuming: " + num);
                    try {
                        Thread.sleep(150); // Simulate some processing
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    // Example 2: ConcurrentLinkedQueue - Safe concurrent iteration without external sync
    public static void demonstrateConcurrentLinkedQueue() throws InterruptedException {
        final ConcurrentLinkedQueue<Integer> concurrentQueue = new ConcurrentLinkedQueue<>();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                concurrentQueue.offer(i);
                System.out.println("Produced: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(50); // Wait for some items to be produced
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            
            // No need for external synchronization - iterator is safe
            System.out.println("ConcurrentLinkedQueue Contents (snapshot at iteration start): " + concurrentQueue);
            for (Integer num : concurrentQueue) {
                System.out.println("  Consuming: " + num);
                try {
                    Thread.sleep(150); // Simulate some processing
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    // Example 3: Demonstrating the key difference with poll() operations
    public static void demonstrateIterationSafety() throws InterruptedException {
        System.out.println("\nWith LinkedList + Collections.synchronizedList:");
        List<String> syncQueue = Collections.synchronizedList(new LinkedList<>());
        syncQueue.add("A");
        syncQueue.add("B");
        syncQueue.add("C");

        Thread pollingThread = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            syncQueue.remove(0); // Remove A
            System.out.println("Removed from syncQueue");
            syncQueue.add("D"); // Add D
            System.out.println("Added D to syncQueue");
        });

        try {
            pollingThread.start();
            // Iterator may face ConcurrentModificationException
            System.out.println("Starting iteration on syncQueue...");
            for (String s : syncQueue) {
                System.out.println("  Reading: " + s);
                Thread.sleep(200);
            }
        } catch (Exception e) {
            System.out.println("  Error during iteration: " + e.getClass().getSimpleName());
        }
        pollingThread.join();

        System.out.println("\nWith ConcurrentLinkedQueue:");
        ConcurrentLinkedQueue<String> concurrentQueue = new ConcurrentLinkedQueue<>();
        concurrentQueue.offer("A");
        concurrentQueue.offer("B");
        concurrentQueue.offer("C");

        Thread pollingThread2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            concurrentQueue.poll(); // Remove A
            System.out.println("Polled from concurrentQueue");
            concurrentQueue.offer("D"); // Add D
            System.out.println("Added D to concurrentQueue");
        });

        pollingThread2.start();
        // Iterator gets snapshot at start time - no exception
        System.out.println("Starting iteration on concurrentQueue...");
        for (String s : concurrentQueue) {
            System.out.println("  Reading: " + s);
            Thread.sleep(200);
        }
        pollingThread2.join();

        System.out.println("\nNote: ConcurrentLinkedQueue uses lock-free algorithm (Compare-And-Swap)");
    }
}
