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

    public static Integer factorial(int n)
    {
        Integer ret = 0;
        for (int i = 1; i <= n; ++i)
            ret *= i;
        return ret;
    }

    public static Integer sum(int n)
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
//
//        Callable<Integer> task1 = () -> {
//                System.out.println("Run task1");
//                TimeUnit.SECONDS.sleep(2);
//                return factorial(10);
//        };
//        Future<Integer> future1 = executor.submit(task1);
//
//        Callable<Integer> task2 = () -> {
//            System.out.println("Run task2");
//            Integer sum = sum(150);
//            sum += future1.get();
//            return sum;
//        };
//        Future<Integer> future2 = executor.submit(task2);
//
//        Callable<Integer> task3 = () -> {
//            System.out.println("Run task3");
//            return factorial(3);
//        };
//        Future<Integer> future3 = executor.submit(task3);
//
//        Callable<Integer> task4 = () -> {
//            System.out.println("Run task4");
//            return future3.get() + future2.get();
//        };
//        Future<Integer> future4 = executor.submit(task4);
//
//        Integer result = future4.get();
        
        
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Run task1");
//            TimeUnit.SECONDS.sleep(2);
            return 5;//factorial(5);
        }, executor);

   //     CompletableFuture<Integer> cf2 = cf1.thenRun(() -> {System.out.println("r t 2");}).thenApply(f -> f + sum(5));
        CompletableFuture<Integer> cf2 = cf1.thenApply(f -> {
        	return f + sum(3);
        });   
        CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Run task3");
            return 10;//factorial(4);
        }, executor);
        
        CompletableFuture<Integer> cf4 = cf2.thenApply(f -> {
			try {
				return f + cf3.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return f;
		});

        Integer result = cf4.get();
        
        System.out.println("result: " + result); //5! + 5+ +4! = 159
        return result;
    }

}

