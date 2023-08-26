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

import java.util.HashMap;
import java.util.Map;

/**
 * Map of arbitrary values that are able to be changed, mapped to their updated value
 */
@SuppressWarnings("serial")
public class UpdatedValues extends HashMap<String, Object>
{
    /**
     * Instantiates a new updated values.
     *
     * @param map the map
     */
    public UpdatedValues(Map<String, Object> map)
    {
        super(map);
    }

    /**
     * Builder.
     *
     * @return the updated values. builder
     */
    public static UpdatedValues.Builder Builder()
    {
        return new UpdatedValues.Builder();
    }

    /**
     * The Class Builder.
     */
    public static class Builder
    {
        /** The obj map. */
        private Map<String, Object> objMap;

        /**
         * Instantiates a new builder.
         */
        private Builder()
        {
            this.objMap = new HashMap<>();
        }

        /**
         * Builds the update.
         *
         * @return the updated values
         */
        public final UpdatedValues buildUpdate()
        {
            return new UpdatedValues(this.objMap);
        }

        /**
         * Append.
         *
         * @param fieldName the field name
         * @param v1 the v 1
         * @param v2 the v 2
         * @return the builder
         */
        public Builder append(String fieldName, String v1, String v2)
        {
            if (!v1.equalsIgnoreCase(v2))
                this.objMap.put(fieldName, v1);
            return this;
        }

        /**
         * Append.
         *
         * @param fieldName the field name
         * @param v1 the v 1
         * @param v2 the v 2
         * @return the builder
         */
        public Builder append(String fieldName, boolean v1, boolean v2)
        {
            if (v1 != v2)
                this.objMap.put(fieldName, v1);
            return this;
        }

        /**
         * Append.
         *
         * @param fieldName the field name
         * @param v1 the v 1
         * @param v2 the v 2
         * @return the builder
         */
        public Builder append(String fieldName, int v1, int v2)
        {
            if (v1 != v2)
                this.objMap.put(fieldName, v1);
            return this;
        }

        /**
         * Append.
         *
         * @param fieldName the field name
         * @param v1 the v 1
         * @param v2 the v 2
         * @return the builder
         */
        public Builder append(String fieldName, long v1, long v2)
        {
            if (v1 != v2)
                this.objMap.put(fieldName, v1);
            return this;
        }

        /**
         * Append diff.
         *
         * @param fieldName the field name
         * @param v1 the v 1
         * @return the builder
         */
        public Builder appendDiff(String fieldName, UpdatedValues v1)
        {
            if (!v1.isEmpty())
                this.objMap.put(fieldName, v1);
            return this;
        }
    }
}
