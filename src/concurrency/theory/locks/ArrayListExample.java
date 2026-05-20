package concurrency.theory.locks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListExample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Example 1: ArrayList with Collections.synchronizedList (Blocking Iterator) ---");
        demonstrateSynchronizedList();

        System.out.println("\n--- Example 2: CopyOnWriteArrayList (Safe Concurrent Iteration) ---");
        demonstrateCopyOnWriteArrayList();

        System.out.println("\n--- Example 3: Showing Modification During Iteration ---");
        demonstrateModificationIssue();
    }

    // Example 1: Collections.synchronizedList - Iterator can be blocked/inconsistent
    public static void demonstrateSynchronizedList() throws InterruptedException {
        final List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        Thread adder = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                synchronizedList.add(i);
                System.out.println("Added: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread iterator = new Thread(() -> {
            try {
                Thread.sleep(50); // Wait for some items to be added
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            
            // Need to synchronize externally to avoid ConcurrentModificationException
            synchronized (synchronizedList) {
                System.out.println("Synchronized List Contents: " + synchronizedList);
                for (Integer num : synchronizedList) {
                    System.out.println("  - " + num);
                    try {
                        Thread.sleep(150); // Simulate some processing
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        adder.start();
        iterator.start();
        adder.join();
        iterator.join();
    }

    // Example 2: CopyOnWriteArrayList - Safe concurrent iteration
    public static void demonstrateCopyOnWriteArrayList() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> copyOnWriteList = new CopyOnWriteArrayList<>();

        Thread adder = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                copyOnWriteList.add(i);
                System.out.println("Added: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread iterator = new Thread(() -> {
            try {
                Thread.sleep(50); // Wait for some items to be added
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            
            // No need for external synchronization - iterator is safe
            System.out.println("CopyOnWriteArrayList Contents (snapshot at iteration start): " + copyOnWriteList);
            for (Integer num : copyOnWriteList) {
                System.out.println("  - " + num);
                try {
                    Thread.sleep(150); // Simulate some processing
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        adder.start();
        iterator.start();
        adder.join();
        iterator.join();
    }

    // Example 3: Demonstrating the key difference
    public static void demonstrateModificationIssue() throws InterruptedException {
        System.out.println("\nWith ArrayList + synchronizedList:");
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        syncList.add("A");
        syncList.add("B");
        syncList.add("C");

        Thread modifier = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            syncList.add("D");
            syncList.add("E");
            System.out.println("Added D and E to syncList");
        });

        try {
            modifier.start();
            // Iterator sees snapshot at start, may get ConcurrentModificationException
            for (String s : syncList) {
                System.out.println("Reading: " + s);
                Thread.sleep(200);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getClass().getSimpleName());
        }
        modifier.join();

        System.out.println("\nWith CopyOnWriteArrayList:");
        CopyOnWriteArrayList<String> cowaList = new CopyOnWriteArrayList<>();
        cowaList.add("A");
        cowaList.add("B");
        cowaList.add("C");

        Thread modifier2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cowaList.add("D");
            cowaList.add("E");
            System.out.println("Added D and E to cowaList");
        });

        modifier2.start();
        // Iterator gets snapshot at start time - no exception
        for (String s : cowaList) {
            System.out.println("Reading: " + s);
            Thread.sleep(200);
        }
        modifier2.join();
    }
}
