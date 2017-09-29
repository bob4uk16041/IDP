/*
 For the Future API: please write sample 
application that will perform some dummy 
computation, pass this computation to 2 second future; 
second future should process the result of computation from the first future. Meanwhile launch some 
computation with in the 3rd future, and write 4th future that will process the results from 2nd and 3rd 
computations
*/
import java.util.concurrent.*;


public class FutureTask {

    private static Integer factorial(int n)
    {
        Integer ret = 0;
        for (int i = 1; i <= n; ++i)
            ret *= i;
        return ret;
    }

    private static Integer sum(int n)
    {
        Integer sum = 0;
        Integer i = 0;
        while (i < n) {
            sum += i;
            i++;
        }
        return sum;
    }

    public static void main(String [] args) throws ExecutionException, InterruptedException {
        task();
    }

    public static Integer task() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Run task1");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return factorial(15);
        }, executor);
        CompletableFuture<Integer> cf2 = cf1.thenApply(f -> {
            System.out.println("Run task2");
        	return f + sum(500);
        });   
        CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Run task3");
            return factorial(24);
        }, executor);

        CompletableFuture<Integer> cf4 =cf2.thenCombine(cf3, (f3, f2) -> {
             System.out.println("Run task4");
             return f3 + f2;
        });
        Integer result = cf4.get();
        System.out.println("result: " + result);
        return result;
    }

}

