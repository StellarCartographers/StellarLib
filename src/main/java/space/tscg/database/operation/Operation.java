package space.tscg.database.operation;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import panda.std.Result;

public interface Operation<T> extends Supplier<T>
{
    default void after(Consumer<T> success)
    {
        if(operationSucceded())
            success.accept(get());
    }
    
    default void after(Consumer<T> success, Runnable error)
    {
        if(operationSucceded())
            success.accept(get());
        else   
            error.run();
    }
    
    default <VALUE, ERROR> Result<VALUE, ERROR> toResult(Supplier<VALUE> value, @NotNull Supplier<ERROR> err)
    {
        return Result.when(operationSucceded(), value, err);
    }
    
    default <VALUE, ERROR> Result<VALUE, ERROR> toResult(VALUE value,ERROR err)
    {
        return Result.when(operationSucceded(), value, err);
    }

    boolean operationSucceded();
}
