package executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemo {
    public static void main(String[] args) {

        // Creating threads manually to perform 10 tasks
        for (int i = 1; i < 10; i++) {
            int taskId = i;
            Thread.ofPlatform().start(() -> {
//                System.out.println("Executing -"+ taskId +" task by "+Thread.currentThread().getName());
            });
        }

        // using executor-service to perform 10 tasks
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 1; i < 10; i++) {
            int taskId = i;
            executorService.execute(() -> {
                System.out.println("Executing -"+ taskId +" task by "+Thread.currentThread().getName());
            });
        }

        executorService.shutdown();

    }
}
