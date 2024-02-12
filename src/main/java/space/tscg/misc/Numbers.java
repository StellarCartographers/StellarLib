package space.tscg.misc;

import java.util.function.Function;

public final class Numbers
{
    public static Function<Double, Boolean> gt(double i)
    {
        return t -> Condition.GT.check(t, i);
    }
    
    public static Function<Double, Boolean> ge(double i)
    {
        return t -> Condition.GE.check(t, i);
    }
    
    public static Function<Double, Boolean> lt(double i)
    {
        return t -> Condition.LT.check(t, i);
    }
    
    public static Function<Double, Boolean> le(double i)
    {
        return t -> Condition.LE.check(t, i);
    }
}
