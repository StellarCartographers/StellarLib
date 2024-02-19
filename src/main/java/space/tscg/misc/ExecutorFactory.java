/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.misc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

@Slf4j
public class ExecutorFactory
{
    public static ScheduledExecutorService newScheduledThreadPool(int poolSize, String threadName, boolean isDaemon)
    {
        return Executors.newScheduledThreadPool(poolSize, newThreadFactory(threadName, isDaemon));
    }

    public static ThreadFactory newThreadFactory(String threadName, boolean isdaemon)
    {
        return (r) ->
        {
            Thread t = new Thread(r, threadName);
            t.setDaemon(isdaemon);
            t.setUncaughtExceptionHandler((final Thread thread, final Throwable throwable) -> log.error("There was a uncaught exception in the {} threadpool", thread.getName(), throwable));
            return t;
        };
    }
}
