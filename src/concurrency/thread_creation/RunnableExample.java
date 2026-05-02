package concurrency.thread_creation;

class MyRunnableThread implements Runnable {

    @Override
    public void run() {
        for(int i=0; i<5; i++) {
            System.out.println("Thread " + Thread.currentThread().getId() + " is running: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }
}

public class RunnableExample {
    public static void main(String[] args) {
        MyRunnableThread runnableThread = new MyRunnableThread();
        Thread thread1 = new Thread(runnableThread);
        Thread thread2 = new Thread(runnableThread);
        thread1.start();
        thread2.start();
    }
}
