package concurrency.synchronisation;

public class CountAndSay {

    private int count = 0;
    private final Object lock = new Object();

    public void increment() {
        System.out.println("Non Synchronised part : " + Thread.currentThread().getName());
        synchronized (lock) {
            System.out.println("Synchronized part start increment" + Thread.currentThread().getName());
            count++;
            System.out.println("Counter increased by 1 : " + Thread.currentThread().getName() + " count " + count);
        }
        System.out.println("Non Synchronised part : " + Thread.currentThread().getName());
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        CountAndSay countAndSay = new CountAndSay();
        int numOfThreads = 5;
        Thread[] threads = new Thread[numOfThreads];
        for(int i=0; i<5; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    countAndSay.increment();
                }
            }, "Thread-" + (i+1));
            threads[i].start();
        }
        for(int i=0; i<numOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Final counter value : " + countAndSay.getCount());
    }
}
