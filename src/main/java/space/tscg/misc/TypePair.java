/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.misc;

import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

import space.tscg.api.Diffable;
import space.tscg.collections.DiffMap;

/**
 * Wrapper class for two objects of the same type. Allows for the creation of
 * a DiffMap, which returns ONLY the values from 'newType' that changed / are not equal
 * from the passed 'oldType'
 */
@Value
public class TypePair<TYPE extends Diffable<TYPE>>
{
    private final TYPE oldType;
    private final TYPE newType;
    private DiffMap    diffReference;

    /**
     * Instantiates a new type pair.
     *
     * @param oldType
     *                    the old type
     * @param newType
     *                    the new type
     */
    public TypePair(TYPE oldType, TYPE newType)
    {
        this.oldType = oldType;
        this.newType = newType;
        this.diffReference = newType.diff(oldType);
    }

    private TypePair(Builder<TYPE> builder)
    {
        this(builder.list.get(0), builder.list.get(1));
    }

    public DiffMap getDiffMap()
    {
        return newType.diff(oldType);
    }

    public static <TYPE extends Diffable<TYPE>> Builder<TYPE> Builder()
    {
        return new Builder<>();
    }

    @NoArgsConstructor
    public static class Builder<TYPE extends Diffable<TYPE>>
    {
        private List<TYPE> list = new ArrayList<>();
        private int        cnt  = 0;

        /**
         * Adds the type.
         *
         * @param   type
         *                   the type
         * 
         * @return       this builder
         * 
         * @apiNote      If this builder already has 2 Types then this does nothing
         *               and returns itself, the passed type is not added.
         */
        public TypePair.Builder<TYPE> addType(TYPE type)
        {
            if (cnt < 2)
                list.add(type);
            return this;
        }

        public TypePair<TYPE> build()
        {
            return new TypePair<>(this);
        }
    }
}
