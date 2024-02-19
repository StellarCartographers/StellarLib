package space.tscg.functions;

import java.util.function.Function;

@FunctionalInterface
public interface FunctionSupplier<T> extends Function<T, T>
{
    T supply(T t);
    
    @Override
    default T apply(T t)
    {
        return supply(t);
    }
}
