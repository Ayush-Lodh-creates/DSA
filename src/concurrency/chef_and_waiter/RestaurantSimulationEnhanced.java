package concurrency.chef_and_waiter;

class WaiterThreadEnhanced extends Thread {
    private final Object lock;

    public WaiterThreadEnhanced(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                System.out.println("Waiter : Waiting for food to be ready... " + Thread.currentThread().getId());
                long startTime = System.currentTimeMillis();
                lock.wait(5000);  // Wait for 5 seconds max
                long elapsedTime = System.currentTimeMillis() - startTime;
                
                // Check if timeout occurred (wait completed but no notification)
                if (elapsedTime >= 4900) {  // Close to 5 seconds
                    System.out.println("Waiter " + Thread.currentThread().getId() + " : TIMEOUT! Food took too long!");
                    throw new TimeoutException("Waiter " + Thread.currentThread().getId() + " gave up waiting!");
                }
                
                System.out.println("Waiter " + Thread.currentThread().getId() + " is ready to serve the customer.");
                System.out.println("Waiter : Food is ready! Serving the customer.");
            } catch (InterruptedException e) {
                System.out.println("Waiter : Interrupted while waiting.");
                Thread.currentThread().interrupt();
            } catch (TimeoutException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }
}

class TimeoutException extends Exception {
    public TimeoutException(String message) {
        super(message);
    }
}

class ChefThreadEnhanced extends Thread {
    private final Object lock;

    public ChefThreadEnhanced(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            synchronized (lock) {
                System.out.println("Chef : Food is ready! Notifying all waiters...");
                lock.notifyAll();  // Use notifyAll to wake up ALL waiting threads
            }
        } catch (InterruptedException e) {
            System.out.println("Chef : Interrupted while preparing food.");
            Thread.currentThread().interrupt();
        }
    }
}

public class RestaurantSimulationEnhanced {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread waiter = new WaiterThreadEnhanced(lock);
        Thread waiter2 = new WaiterThreadEnhanced(lock);
        Thread waiter3 = new WaiterThreadEnhanced(lock);
        Thread chef = new ChefThreadEnhanced(lock);
        Thread chef2 = new ChefThreadEnhanced(lock);
        // Thread chef3 = new ChefThreadEnhanced(lock);  // Comment out chef3

        waiter.start();
        waiter2.start();
        waiter3.start();
        chef.start();
        chef2.start();
        // chef3.start();
    }
}

