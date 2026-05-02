package concurrency.chef_and_waiter;

class WaiterThread extends Thread {
    private final Object lock;

    public WaiterThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                System.out.println("Waiter : Waiting for food to be ready... " + Thread.currentThread().getId());
                lock.wait(5000);  // Wait for 5 seconds max
                System.out.println("Waiter " + Thread.currentThread().getId() + " is ready to serve the customer.");
                System.out.println("Waiter : Food is ready! Serving the customer.");
            } catch (InterruptedException e) {
                System.out.println("Waiter : Interrupted while waiting.");
                Thread.currentThread().interrupt();
            }
        }
    }
}

class ChefThread extends Thread {
    private final Object lock;

    public ChefThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            synchronized (lock) {
                System.out.println("Chef : Food is ready! Notifying the waiter...");
                lock.notify();
            }
        } catch (InterruptedException e) {
            System.out.println("Chef : Interrupted while preparing food.");
            Thread.currentThread().interrupt();
        }
    }
}
public class RestaurantSimulation {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread waiter = new WaiterThread(lock);
        Thread waiter2 = new WaiterThread(lock);
        Thread waiter3 = new WaiterThread(lock);
        Thread chef = new ChefThread(lock);
        Thread chef2 = new ChefThread(lock);
        Thread chef3 = new ChefThread(lock);

        waiter.start();
        waiter2.start();
        waiter3.start();
        chef.start();
        chef3.start();
//        chef2.start();
    }
}
