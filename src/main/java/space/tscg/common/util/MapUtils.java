package space.tscg.common.util;

import java.util.Map;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MapUtils
{
    /**
     * Returns {@code true} if the specified map is {@code null} or empty.
     *
     * @param map The map. May be {@code null}.
     *
     * @return {@code true} if the map is {@code null} or empty, else
     *         {@code false}.
     */
    public static boolean isEmpty(final Map<?, ?> map)
    {
        return (map == null) || map.isEmpty();
    }

    /**
     * Returns {@code true} if the specified map is not {@code null} and
     * not empty.
     *
     * @param map The map. May be {@code null}.
     *
     * @return {@code true} if the map is not {@code null} and not empty,
     *         else {@code false}.
     */
    public static boolean isNotEmpty(final Map<?, ?> map)
    {
        return (map != null) && !map.isEmpty();
    }
}
