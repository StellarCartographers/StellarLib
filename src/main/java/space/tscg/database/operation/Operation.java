/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.database.operation;

import panda.std.Result;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface Operation<T> extends Supplier<T>
{
    default void after(Consumer<T> success)
    {
        if (operationSucceded())
            success.accept(get());
    }

    default void after(Consumer<T> success, Runnable error)
    {
        if (operationSucceded())
            success.accept(get());
        else
            error.run();
    }

    default <VALUE, ERROR> Result<VALUE, ERROR> toResult(Supplier<VALUE> value, @NotNull Supplier<ERROR> err)
    {
        return Result.when(operationSucceded(), value, err);
    }

    default <VALUE, ERROR> Result<VALUE, ERROR> toResult(VALUE value, ERROR err)
    {
        return Result.when(operationSucceded(), value, err);
    }

    boolean operationSucceded();
}
