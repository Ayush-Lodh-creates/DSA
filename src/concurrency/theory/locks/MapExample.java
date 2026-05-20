package concurrency.theory.locks;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapExample {

    public static void main(String[] args) throws InterruptedException {
        final Map<Integer, String> hashMap = Collections.synchronizedMap(new HashMap<>());
        final ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();

        Thread hashMapUpdater = new Thread(() -> {
            for(int i=1; i<=5; i++) {
                hashMap.put(i, "Value " + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread hashMapIterator = new Thread(() -> {
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (hashMap) {
                for(Map.Entry<Integer, String> entry : hashMap.entrySet()) {
                    System.out.println("HashMap Entry: " + entry.getKey() + " = " + entry.getValue());
                }
            }
        });

        hashMapUpdater.start();
        hashMapIterator.start();
        hashMapUpdater.join();
        hashMapIterator.join();

        Thread concurrentHashMapUpdater = new Thread(() -> {
            for(int i=1; i<=5; i++) {
                concurrentHashMap.put(i, "Value " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread concurrentHashMapIterator = new Thread(() -> {
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for(Map.Entry<Integer, String> entry : concurrentHashMap.entrySet()) {
                System.out.println("HashMap Entry: " + entry.getKey() + " = " + entry.getValue());
            }
        });

        concurrentHashMapUpdater.start();
        concurrentHashMapIterator.start();
        concurrentHashMapUpdater.join();
        concurrentHashMapIterator.join();
    }
}
