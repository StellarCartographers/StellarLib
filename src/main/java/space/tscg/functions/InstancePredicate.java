/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.functions;

import java.util.function.Predicate;

public class InstancePredicate<T>
{
    private final T            objectInstance;
    private final Predicate<T> predicate;
    private final Boolean            mustEqual;

    public InstancePredicate(T instance, Predicate<T> predicate)
    {
        this(instance, predicate, null);
    }
    
    public InstancePredicate(T instance, Predicate<T> predicate, Boolean mustEqual)
    {
        this.objectInstance = instance;
        this.predicate = predicate;
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
