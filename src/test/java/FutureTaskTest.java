import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FutureTaskTest {

    @Test
    public void test_completed_future() throws Exception {
        Integer taskResult = FutureTask.task();
        CompletableFuture<Integer> alreadyCompleted = CompletableFuture.completedFuture(taskResult);
        assertThat(alreadyCompleted.get(), is(124750));
    }
}
