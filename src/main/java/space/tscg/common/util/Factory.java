package space.tscg.common.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Factory
{

    /**
     * New scheduled thread pool.
     *
     * @param poolSize the pool size
     * @param threadName the thread name
     * @param isDaemon the is daemon
     * @return the scheduled executor service
     */
    public static ScheduledExecutorService newScheduledThreadPool(int poolSize, String threadName, boolean isDaemon)
    {
        return Executors.newScheduledThreadPool(poolSize, newThreadFactory(threadName, isDaemon));
    }

    /**
     * New thread factory.
     *
     * @param threadName the thread name
     * @param isdaemon the isdaemon
     * @return the thread factory
     */
    public static ThreadFactory newThreadFactory(String threadName, boolean isdaemon)
    {
        return r ->
        {
            var t = new Thread(r, threadName);
            t.setDaemon(isdaemon);
            t.setUncaughtExceptionHandler((final var thread, final var throwable) -> log.error("There was a uncaught exception in the {} threadpool", thread.getName(), throwable));
            return t;
        };
    }
}
