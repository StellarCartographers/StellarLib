/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.misc;

import java.io.Serializable;
import java.util.function.Predicate;

public class Conditional
{
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
