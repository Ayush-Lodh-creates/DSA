package concurrency.thread_creation;

import java.util.concurrent.*;

class MyCallable implements Callable<String> {

    private final String name;

    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<5; i++) {
            sb.append("Callable ").append(name).append(" is running: ").append(i).append("\n");
            Thread.sleep(500);
        }
        return sb.toString();
    }
}

public class MyCallableExample {
    public static void main(String[] args) {
        // Create Executor class with fixed thread pools
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Create callable instances
        Callable<String> callable1 = new MyCallable("Task 1");
        Callable<String> callable2 = new MyCallable("Task 2");

        try {
            Future<String> future1 = executor.submit(callable1);
            Future<String> future2 = executor.submit(callable2);

            System.out.println("Result from Task 1");
            System.out.println(future1.get());

            System.out.println("Result from Task 2");
            System.out.println(future2.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Task Interrupted : " +e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}