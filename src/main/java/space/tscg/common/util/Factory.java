/**
 * Copyright (C) 2023  The Stellar Cartographers' Guild
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
