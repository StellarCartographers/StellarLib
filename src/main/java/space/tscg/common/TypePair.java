/**
 * Copyright (C) 2023  The Stellar Cartographers' Guild
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package space.tscg.common;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import lombok.Value;
import space.tscg.api.Diffable;

/**
 * Wrapper class for two objects of the same type. Extends Diffable so implementations
 * can create a UpdatedValues map
 * @param <TYPE>
 */
@Value
public class TypePair<TYPE extends Diffable<TYPE>>
{
    /** The old type. */
    private final TYPE oldType;

    /** The new type. */
    private final TYPE newType;

    /**
     * Instantiates a new type pair.
     *
     * @param oldType the old type
     * @param newType the new type
     */
    public TypePair(TYPE oldType, TYPE newType)
    {
        this.oldType = oldType;
        this.newType = newType;
    }

    /**
     * Instantiates a new type pair.
     *
     * @param typeBuilder the type builder
     */
    private TypePair(Builder<TYPE> typeBuilder)
    {
        this.oldType = typeBuilder.list.get(0);
        this.newType = typeBuilder.list.get(1);
    }

    /**
     * Gets the diff.
     *
     * @return the diff
     */
    public UpdatedValues getDiff()
    {
        return newType.diff(oldType);
    }

    /**
     * Builder.
     *
     * @param <TYPE> the generic type
     * @return the builder
     */
    public static <TYPE extends Diffable<TYPE>> Builder<TYPE> Builder()
    {
        return new Builder<>();
    }

    /**
     * Instantiates a new builder.
     *
     * @param <TYPE> the generic type
     */
    @NoArgsConstructor
    public static class Builder<TYPE extends Diffable<TYPE>>
    {
        private List<TYPE> list = new ArrayList<>();

        private int cnt = 0;

        /**
         * Adds the type.
         *
         * @param type the type
         * @return this builder
         * @apiNote If this builder already has 2 Types then this does nothing
         *          and returns itself, the passed type is not added.
         */
        public TypePair.Builder<TYPE> addType(TYPE type)
        {
            if (cnt < 2)
                list.add(type);
            return this;
        }

        /**
         * Builds the.
         *
         * @return the type pair
         */
        public TypePair<TYPE> build()
        {
            return new TypePair<>(this);
        }
    }
}
