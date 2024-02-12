/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.misc;

import java.util.function.BiFunction;

@SuppressWarnings({"rawtypes", "unchecked"})
public enum Condition
{
    GT((BiFunction<Double, Double, Boolean>) (i1, i2) -> i1 > i2),
    LT((BiFunction<Double, Double, Boolean>) (i1, i2) -> i1 < i2),
    GE((BiFunction<Double, Double, Boolean>) (i1, i2) -> i1 >= i2),
    LE((BiFunction<Double, Double, Boolean>) (i1, i2) -> i1 <= i2),;

    private BiFunction<Double, Double, Boolean> function;

    Condition(BiFunction function)
    {
        this.function = function;
    }

    public boolean check(Object o1, Object o2)
    {
        try
        {
            var d1 = Double.valueOf(o1.toString());
            var d2 = Double.valueOf(o2.toString());
            return function.apply(d1, d2);
        } catch (ClassCastException e)
        {
            throw new IllegalArgumentException();
        }
    }
}
