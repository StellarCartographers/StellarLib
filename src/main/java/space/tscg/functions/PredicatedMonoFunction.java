package space.tscg.functions;

import lombok.AllArgsConstructor;

import java.util.function.*;

@AllArgsConstructor(staticName = "of")
public class PredicatedMonoFunction<T>
{
    private final Predicate<T> predicate;
    private final Function<T, T> function;

    public boolean test(T t)
    {
        return predicate.test(t);
    }
    
    public T function(T t)
    {
        return function.apply(t);
    }
}
