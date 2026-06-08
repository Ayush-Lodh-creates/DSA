package LLD.standard.job_scheduler;

import java.util.PriorityQueue;

public class JobScheduler {

    PriorityQueue<Job> tasks;
    private final Object lock;
    private volatile boolean running;

    public JobScheduler() {
        tasks = new PriorityQueue<>((a, b) ->
                Long.compare(a.getScheduledTime(), b.getScheduledTime()));
        lock = new Object();
        running = true;

        startWorker();
    }

    public void scheduleJob(Job job) {
        synchronized (lock) {
            tasks.offer(job);
            lock.notify();
        }
    }

    private void startWorker() {
        Thread worker = new Thread(() -> {
            while(running) {
                try {
                    synchronized (lock) {
                        while (tasks.isEmpty()) {
                            lock.wait();
                        }

                        Job nextJob = tasks.peek();
                        long now = System.currentTimeMillis();
                        long delay = nextJob.getScheduledTime() - now;
                        if(delay > 0) {
                            lock.wait(delay);
                            continue;
                        }

                        tasks.poll();
                        execute(nextJob);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        worker.setDaemon(true);
        worker.start();
    }

    private void execute(Job job) {
        try {
            job.getTask().run();
        } catch (Exception e) {
            System.out.println("Error executing job: " + e.getMessage());
        }
    }

    public void shutdown() {
        running = false;
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
