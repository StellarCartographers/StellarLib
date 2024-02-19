/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections;

import java.lang.reflect.Field;
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

    @SuppressWarnings("unchecked")
    public static <T> DiffMap fromObjects(T obj1, T obj2)
    {
        Class<T> class1 = (Class<T>) obj1.getClass();
        Class<T> class2 = (Class<T>) obj2.getClass();
        if (class1.equals(class2))
        {
            DiffMap.Builder builder = DiffMap.Builder();
            for (int i = 0; i < class1.getDeclaredFields().length; i++)
            {
                Field f1 = class1.getDeclaredFields()[i];
                f1.setAccessible(true);
                Field f2 = class2.getDeclaredFields()[i];
                f2.setAccessible(true);
                try
                {
                    var t1 = f1.get(obj1);
                    var t2 = f2.get(obj2);
                    if (t1 instanceof Enum && t2 instanceof Enum)
                    {
                        builder.appendEnum(f1.getName(), (Enum<?>) t1, (Enum<?>) t2);
                    }
                    if (t1 instanceof String && t2 instanceof String)
                    {
                        builder.append(f1.getName(), (String) t1, (String) t2);
                    }
                    if (t1 instanceof Boolean && t2 instanceof Boolean)
                    {
                        builder.append(f1.getName(), (boolean) t1, (boolean) t2);
                    }
                    if (t1 instanceof Integer && t2 instanceof Integer)
                    {
                        builder.append(f1.getName(), (int) t1, (int) t2);
                    }
                    if (t1 instanceof Long && t2 instanceof Long)
                    {
                        builder.append(f1.getName(), (long) t1, (long) t2);
                    }
                } catch (IllegalArgumentException | IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
            return builder.build();
        }
        return null;
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
