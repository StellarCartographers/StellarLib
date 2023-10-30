package space.tscg.collections;

import java.util.List;

import lombok.experimental.UtilityClass;
import space.tscg.collections.list.UnalterableList;

@UtilityClass
public class ListUtility
{
    public static <E> List<E> unalterableList(final List<? extends E> list)
    {
        return UnalterableList.unmodifiableList(list);
    }
}
