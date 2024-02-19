/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.functions;

import java.io.Serializable;
import java.util.function.*;

public class Conditional
{
    public static class Digit
    {
        private Number number;

        private Digit(Number number)
        {
            this.number = number;
        }

        public boolean is(NumberConditions condition, Number i)
        {
            return condition.check(number, i);
        }

        public Boolean gt(Number i)
        {
            return NumberConditions.GT.check(number, i);
        }

        public Boolean ge(Number i)
        {
            return NumberConditions.GE.check(number, i);
        }

        public Boolean lt(Number i)
        {
            return NumberConditions.LT.check(number, i);
        }

        public Boolean le(Number i)
        {
            return NumberConditions.LE.check(number, i);
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        public enum NumberConditions
        {
            GT((BiFunction<Number, Number, Boolean>) (i1, i2) -> i1.doubleValue() > i2.doubleValue()),
            LT((BiFunction<Number, Number, Boolean>) (i1, i2) -> i1.doubleValue() < i2.doubleValue()),
            GE((BiFunction<Number, Number, Boolean>) (i1, i2) -> i1.doubleValue() >= i2.doubleValue()),
            LE((BiFunction<Number, Number, Boolean>) (i1, i2) -> i1.doubleValue() <= i2.doubleValue()),;

            private BiFunction<Number, Number, Boolean> function;

            NumberConditions(BiFunction function)
            {
                this.function = function;
            }

            public boolean check(Number o1, Number o2)
            {
                try
                {
                    return function.apply(o1, o2);
                } catch (ClassCastException e)
                {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    public static Digit Number(Number number)
    {
        return new Digit(number);
    }

    public static Predicate<Boolean> alwaysTrue = Boolean::booleanValue;

    public static <T extends Object> Predicate<T> instanceOf(Class<?> clazz)
    {
        return new InstanceOfPredicate<>(clazz);
    }

    private static class InstanceOfPredicate<T extends Object> implements Predicate<T>, Serializable
    {
        private final Class<?> clazz;

        private InstanceOfPredicate(Class<?> clazz)
        {
            this.clazz = checkNotNull(clazz);
        }

        @Override
        public boolean test(T o)
        {
            return clazz.isInstance(o);
        }

        @Override
        public int hashCode()
        {
            return clazz.hashCode();
        }

        @Override
        public boolean equals(Object obj)
        {
            if (obj instanceof InstanceOfPredicate)
            {
                InstanceOfPredicate<?> that = (InstanceOfPredicate<?>) obj;
                return clazz == that.clazz;
            }
            return false;
        }

        @Override
        public String toString()
        {
            return "Predicates.instanceOf(" + clazz.getName() + ")";
        }

        private static final long serialVersionUID = 0;
    }

    public static <T> T checkNotNull(T reference)
    {
        if (reference == null)
        {
            throw new NullPointerException();
        }
        return reference;
    }
}
