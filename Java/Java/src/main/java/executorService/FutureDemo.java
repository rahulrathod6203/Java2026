package executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            Future<Integer> future = executorService.submit(() -> 10 + 20);

            int result = future.get(); // has blocking nature

            if(future.isDone()){ // check if the task is done
                System.out.println(future.get());
            }

            int resultAfterThreeSec = future.get(3, TimeUnit.SECONDS); // waits up to give time then produces the
            // result
            System.out.println(resultAfterThreeSec);
//            future.cancel(true); // cancel the task

//            future.isCancelled(); // check if the task is cancelled

            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }

    // Limitations
    /*
    1. Blocking Nature of get()
    2. No Callback Mechanism -> Cannot attach logic when task completes automatically
    3. Difficult Exception Handling -> Exceptions are wrapped in ExecutionException
    4. No Task Chaining -> Cannot easily chain multiple async operations


    // When to use Future
    1. Simple async tasks
    2. You just need result later
    3. No complex chaining required

    */
}
