package concurrency.thread_pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerThread implements Runnable {

    private final int taskId;

    WorkerThread(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is executing task " + taskId);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Task " + taskId + " was interrupted");
        }
        System.out.println(Thread.currentThread().getName() + " has completed task " + taskId);
    }
}

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            WorkerThread worker = new WorkerThread(i);
            executorService.submit(worker);
        }

        executorService.shutdown();
    }
}
