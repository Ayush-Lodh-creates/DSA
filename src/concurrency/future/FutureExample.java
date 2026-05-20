package concurrency.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(2000);
                return "Task Completed";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            System.out.println("Task completed, doing other task");
            String result = future.get();
            System.out.println("Result from future: " + result);
            System.out.println("Is task done " + future.isDone());
        } catch (Exception e) {
            System.out.println("Exception while getting future result: " + e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }
}
