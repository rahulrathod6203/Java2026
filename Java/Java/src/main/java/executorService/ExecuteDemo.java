package executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {

            for (int i = 1; i < 2; i++) {
                int taskId = i;
                executorService.execute(() ->
                        System.out.println("Executing task-" + taskId + " by thread" + Thread.currentThread().getName()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }
}