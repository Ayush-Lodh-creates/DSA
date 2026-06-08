package LLD.standard.job_scheduler;

public class Job {

    private int id;
    private Runnable task;
    private long scheduledTime;

    public Job(int id, Runnable task, long scheduledTime) {
        this.id = id;
        this.task = task;
        this.scheduledTime = scheduledTime;
    }

    public int getId() {
        return id;
    }

    public Runnable getTask() {
        return task;
    }

    public long getScheduledTime() {
        return scheduledTime;
    }
}
