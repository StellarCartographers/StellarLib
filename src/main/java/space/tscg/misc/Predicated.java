/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.misc;

import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

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
