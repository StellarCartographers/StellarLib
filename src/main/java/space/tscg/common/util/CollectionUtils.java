package space.tscg.common.util;

import java.util.Collection;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CollectionUtils
{

    /**
     * Returns {@code true} if the specified collection is {@code null} or
     * empty.
     *
     * @param collection The collection. May be {@code null}.
     *
     * @return {@code true} if the collection is {@code null} or empty,
     *         else {@code false}.
     */
    public static boolean isEmpty(final Collection<?> collection)
    {
        return (collection == null) || collection.isEmpty();
    }

    /**
     * Returns {@code true} if the specified collection is not empty.
     *
     * @param collection The collection. May be {@code null}.
     *
     * @return {@code true} if the collection is not empty, else
     *         {@code false}.
     */
    public static boolean isNotEmpty(final Collection<?> collection)
    {
        return (collection != null) && !collection.isEmpty();
    }

    /**
     * Returns {@code true} if the specified collection contains the
     * specified item.
     *
     * @param collection The collection. May be {@code null}.
     * @param item       The item. Should not be {@code null}.
     *
     * @return {@code true} if the collection is not empty and contains the
     *         item, else {@code false}.
     */
    public static <T> boolean contains(final Collection<T> collection, final T item)
    {
        return isNotEmpty(collection) && collection.contains(item);
    }

    /**
     * Returns {@code true} if the specified collections intersect.
     *
     * @param a The first collection. May be {@code null}.
     * @param b The second collection. May be {@code null}.
     *
     * @return {@code true} if the collections intersect, else
     *         {@code false}.
     */
    public static <T> boolean intersect(final Collection<T> a, final Collection<T> b)
    {
        if (isEmpty(a) || isEmpty(b))
        {
            return false;
        }

        for (T item : a)
        {
            if (b.contains(item))
            {
                return true;
            }
        }

        return false;
    }
}
