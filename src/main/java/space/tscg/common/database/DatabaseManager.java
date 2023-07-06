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
