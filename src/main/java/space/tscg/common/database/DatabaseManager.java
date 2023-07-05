package space.tscg.common.database;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;

import space.tscg.common.util.Factory;

public class DatabaseManager
{

    static final ScheduledExecutorService service = Factory.newScheduledThreadPool(1, "SCG", true);

    static void queue(Callable<?> action) {
        service.submit(action);
    }

    static void queue(Runnable runnable) {
        service.submit(runnable);
    }
}
