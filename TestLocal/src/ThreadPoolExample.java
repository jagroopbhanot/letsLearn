import aop.logging.TrackElapsedTime;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {


    public static void main(String args[]) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i =0; i<100; i++){
            service.submit(new Task(i));
        }
        service.shutdown();
    }

}   //cascade propogation

final class Task implements Runnable{
    private int taskId;

    public Task(int id){
        this.taskId = id;
    }

    @Override
    @TrackElapsedTime
    public void run() {
        System.out.println("Task ID : " + this.taskId +" performed by "
                + Thread.currentThread().getName());
    }

}


