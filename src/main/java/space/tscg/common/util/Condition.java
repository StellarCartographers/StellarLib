package space.tscg.common.util;

import java.util.function.BiFunction;

@SuppressWarnings({"rawtypes", "unchecked"})
public enum Condition {
    GT((BiFunction<Integer, Integer, Boolean>) (i1, i2) -> i1 > i2);

    private BiFunction<Object, Object, Boolean> function;

    Condition(BiFunction function)
    {
        this.function = function;
    }
    
    public boolean check(Object o1, Object o2)
    {
        try {
            return function.apply(o1, o2);
        } catch (ClassCastException e) {
            return true;
        }
    }
}