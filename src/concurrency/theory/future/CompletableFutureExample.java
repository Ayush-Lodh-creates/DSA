package concurrency.theory.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {

    public static void main3(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Result from CompletableFuture";
        }).thenAccept(result -> {
            System.out.println("Completable future result : " + result);
            System.out.println("Processing after Completable future result");
        });

        System.out.println("Main thread is free to do other tasks while waiting");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main2() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return "Future-1";
            } catch (InterruptedException e) {
                return "Interrupted";
            }
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return "Future-1";
            } catch (InterruptedException e) {
                return "Interrupted";
            }
        });
        CompletableFuture<String> combined = future1.thenCombine(future2, (result1, result2) -> {
            return result1 + result2;
        });
        System.out.println("Combined result : " + combined.join());
        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2);
        allOf.thenRun(() -> System.out.println("Both jobs are completed"));
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2);
        System.out.println("First Completed " +anyOf.join());
    }

    public static void main(String[] args) {
        System.out.println("=== DEMO 1: orTimeout - Throws Exception on Timeout ===");
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("[Task1] Starting - will sleep for 3 seconds");
                Thread.sleep(3000);
                System.out.println("[Task1] Completed after 3 seconds");
                return "Task Completed";
            } catch (InterruptedException e) {
                return "Task Aborted";
            }
        });
        
        System.out.println("[Main] Starting - current time: " + System.currentTimeMillis());
        try {
            System.out.println("[Main] Setting orTimeout (2 seconds)");
            CompletableFuture<String> withTimeout = future1.orTimeout(2, TimeUnit.SECONDS);
            System.out.println("Result: " + withTimeout.join());
        } catch (CompletionException ex) {
            System.out.println("[Main] Timeout caused : " + ex.getCause().getClass().getSimpleName());
            System.out.println("[Main] After timeout - elapsed time: " + System.currentTimeMillis());
        }

        System.out.println("\n=== DEMO 2: completeOnTimeout - Returns Default Value on Timeout ===");
        // Create a NEW future for the second demo (don't reuse the first one!)
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("[Task2] Starting - will sleep for 3 seconds");
                Thread.sleep(3000);
                System.out.println("[Task2] Completed after 3 seconds");
                return "Task Completed";
            } catch (InterruptedException e) {
                return "Task Aborted";
            }
        });
        
        System.out.println("[Main] Starting - current time: " + System.currentTimeMillis());
        try {
            System.out.println("[Main] Setting completeOnTimeout (2 seconds) with default value");
            CompletableFuture<String> withDefault = future2.completeOnTimeout("Default Value", 2, TimeUnit.SECONDS);
            System.out.println("[Main] Joining withDefault future");
            System.out.println("Result: " + withDefault.get());
        } catch (CompletionException | ExecutionException | InterruptedException ex) {
            System.out.println("[Main] Error: " + ex.getClass().getSimpleName());
            if (ex.getCause() != null) {
                System.out.println("[Main] Caused by: " + ex.getCause().getClass().getSimpleName());
            }
        }
        System.out.println("[Main] Completed");
    }
}
