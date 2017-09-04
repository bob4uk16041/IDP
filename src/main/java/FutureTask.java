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
        Integer t = task();
    }

    public static Integer task() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Callable task1 = () -> {
                System.out.println("Run task1");
                TimeUnit.SECONDS.sleep(2);
                return factorial(10);
        };
        Future<Integer> future1 = executor.submit(task1);

        Callable task2 = () -> {
            System.out.println("Run task2");
            Integer sum = sum(150);
            sum += future1.get();
            return sum;
        };
        Future<Integer> future2 = executor.submit(task2);

        Callable task3 = () -> {
            System.out.println("Run task3");
            return factorial(3);
        };
        Future<Integer> future3 = executor.submit(task3);

        Callable task4 = () -> {
            System.out.println("Run task4");
            return future3.get() + future2.get();
        };
        Future<Integer> future4 = executor.submit(task4);

        Integer result = future4.get();
        System.out.println("result: " + result);
        return result;
    }

}

