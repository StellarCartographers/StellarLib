/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * Map of arbitrary values that are able to be changed, mapped to their updated value
 */
@SuppressWarnings("serial")
public class DiffMap extends HashMap<String, Object>
{
    /**
     * Creates a new ChangeMap object from the given map.
     *
     * @param map
     */
    public DiffMap(Map<String, Object> map)
    {
        super(map);
    }

    public static DiffMap.Builder Builder()
    {
        return new DiffMap.Builder();
    }

    public static class Builder
    {
        private Map<String, Object> objMap;

        private Builder()
        {
            this.objMap = new HashMap<>();
        }

        public final DiffMap build()
        {
            return new DiffMap(this.objMap);
        }

        public Builder appendEnum(String fieldName, Enum<?> e1, Enum<?> e2)
        {
            return this.append(fieldName, e1.toString(), e2.toString());
        }

        public Builder append(String fieldName, String v1, String v2)
        {
            if (!v1.equalsIgnoreCase(v2))
                this.objMap.put(fieldName, v1);
            return this;
        }

        public Builder append(String fieldName, boolean v1, boolean v2)
        {
            if (v1 != v2)
                this.objMap.put(fieldName, v1);
            return this;
        }

        public Builder append(String fieldName, int v1, int v2)
        {
            if (v1 != v2)
                this.objMap.put(fieldName, v1);
            return this;
        }

        public Builder append(String fieldName, long v1, long v2)
        {
            if (v1 != v2)
                this.objMap.put(fieldName, v1);
            return this;
        }

        public Builder appendDiff(String fieldName, DiffMap v1)
        {
            if (!v1.isEmpty())
                this.objMap.put(fieldName, v1);
            return this;
        }
    }
}
