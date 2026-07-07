package executorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SubmitDemo {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            Future<Integer> future = executorService.submit(() -> 10 + 20);
            int result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }
}
