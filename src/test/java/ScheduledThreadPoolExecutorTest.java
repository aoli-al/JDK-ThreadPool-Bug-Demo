import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pastalab.fray.core.scheduler.ReplayScheduler;
import org.pastalab.fray.junit.junit5.FrayTestExtension;
import org.pastalab.fray.junit.junit5.annotations.ConcurrencyTest;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@ExtendWith(FrayTestExtension.class)
public class ScheduledThreadPoolExecutorTest {
    private void test() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        new Thread(() -> {
            executor.shutdown();
        }).start();
        try {
            ScheduledFuture<?> future = executor.schedule(() -> {
                Thread.yield();
            }, 10, TimeUnit.MILLISECONDS);
            try {
                future.get();
                Thread.yield();
            } catch (Throwable e) {}
        } catch (RejectedExecutionException e) {}
    }

    @Test
    public void testScheduledThreadPoolExecutor() {
        test();
    }

    @ConcurrencyTest(
            iterations = 1000
    )
    public void testWithFray() {
        test();
    }

    @ConcurrencyTest(
            replay = ""
    )
    public void replayWithFray() {
        test();
    }

}
