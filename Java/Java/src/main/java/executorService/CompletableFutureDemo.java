package executorService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Rahul");
        String res = future.get();
        System.out.println(res);

        CompletableFuture<Void> future1 = CompletableFuture
                .supplyAsync(() -> "Rahul")
                .thenApply(String::toUpperCase)
                .thenAccept(System.out::println)
                .thenRun(() -> System.out.println("Name transformed to upperCase successfully!"));


        CompletableFuture<Void> orderFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("User Placing order");
                    return "Pizza";
                })
                .thenApply(food -> {
                    System.out.println("Preparing " + food);
                    return food + " Ready";
                })
                .thenApply(order -> {
                    System.out.println("Calculating bill");
                    int foodPrice = 300;
                    int deliveryCharge = 50;
                    return foodPrice + deliveryCharge;
                })
                .thenAccept(totalBill -> {
                    System.out.println("Please pay " + totalBill);
                })
                .thenRun(() -> {
                    System.out.println("Pizza delivered successfully!");
                })
                .whenComplete((result, exception) -> {
                    if (exception == null) {
                        System.out.println("Order processed successfully!");
                    }
                });

        orderFuture.join();

    }
}
