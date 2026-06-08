package LLD.standard.job_scheduler;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Job job1 = new Job(
                1,
                () -> System.out.println("Executing job 1 successfully completed"),
                System.currentTimeMillis() + 3000
        );

        Job job2 = new Job(
                2,
                () -> System.out.println("Executing job 2 successfully completed"),
                System.currentTimeMillis() + 1000
        );

        Job job3 = new Job(
                3,
                () -> System.out.println("Executing job 3 successfully completed"),
                System.currentTimeMillis() + 5500
        );

        JobScheduler jobScheduler = new JobScheduler();
        jobScheduler.scheduleJob(job1);
        jobScheduler.scheduleJob(job2);
        jobScheduler.scheduleJob(job3);
        Thread.sleep(10000);
        jobScheduler.shutdown();
    }
}
