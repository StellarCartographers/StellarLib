package space.tscg.misc;

import java.util.function.Predicate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Predicated<T>
{
    private final T            objectInstance;
    private final Predicate<T> predicate;
    private Boolean            mustEqual = null;

    public Predicated(T instance, Predicate<T> predicate, Boolean mustEqual)
    {
        this(instance, predicate);
        this.mustEqual = mustEqual;
    }

    public boolean isTrue()
    {
        var test = this.predicate.test(this.objectInstance);
        return this.mustEqual != null ? test == this.mustEqual.booleanValue() : test == true;
    }
    
    public boolean isFalse()
    {
        var test = this.predicate.test(this.objectInstance);
        return this.mustEqual != null ? test != this.mustEqual.booleanValue() : test == false;
    }
}
