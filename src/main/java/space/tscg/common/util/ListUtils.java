package space.tscg.common.util;

import java.util.LinkedList;
import java.util.List;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ListUtils
{
    public static <T> List<T> removeNullItems(final List<T> list)
    {
        if (list == null)
        {
            return null;
        }
        List<T> out = new LinkedList<>();
        for (T item : list)
        {
            if (item != null)
            {
                out.add(item);
            }
        }
        return out;
    }
}
