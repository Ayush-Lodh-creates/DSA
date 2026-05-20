package concurrency.theory.synchronisation;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity = 5;

    public void produce() throws InterruptedException {
        int value = 0;
        while(true) {
            synchronized(this) {
                while(buffer.size() == capacity) {
                    System.out.println("Buffer is full. Producer is waiting ...");
                    wait();
                }
                System.out.println("Producer produced : " + value);
                buffer.add(value++);
                notifyAll();
            }
            Thread.sleep(1000);
        }
    }

    public void consume() throws InterruptedException {
        while(true) {
            synchronized (this) {
                while (buffer.isEmpty()) {
                    System.out.println("Buffer is empty. Consumer is waiting ...");
                    wait();
                }
                int value = buffer.poll();
                System.out.println("Consumer consumed : " + value);
                notifyAll();
            }
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Producer Thread");
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Consumer Thread");
        producer.start();
        consumer.start();
    }
}
