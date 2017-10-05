import org.junit.Before;
import org.junit.Test;
import reentrantLocks.Count;
import reentrantLocks.ReentrantLocks;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.Awaitility.to;
import static org.hamcrest.core.IsEqual.equalTo;

public class ReentrantLocksTest {
    private Count count;

    @Before
    public void before() {
        count = new Count();
    }


    @Test
    public void crateListOfNamesTest() throws Exception {
        ReentrantLocks.crateListOfNames(count);
        await().untilCall( to(count.getListOfThreadNames()).size(), equalTo(10));
        await().atMost(10, TimeUnit.SECONDS).until(() -> {
            int i = 0;
            boolean isOkList = false;
            for (String name: count.getListOfThreadNames()){
                isOkList = (i % 2 == 0) ? name.equals("Thread 1") : name.equals("Thread 2");
                i++;
            }
            return isOkList;
        });
    }

    private Callable<List<String>> listOfNames() {
        return new Callable<List<String>>() {
            public List<String> call() throws Exception {
                return count.getListOfThreadNames();
            }
        };
    }
}
