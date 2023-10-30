package space.tscg.collections.iterator;

import space.tscg.collections.Get;

public interface IterableGet<K, V> extends Get<K, V>
{
    /**
     * Obtains a {@code MapIterator} over the map.
     * <p>
     * A map iterator is an efficient way of iterating over maps.
     * There is no need to access the entry set or use Map Entry objects.
     * 
     * <pre>
     * IterableMap&lt;String, Integer&gt; map = new HashedMap&lt;String, Integer&gt;();
     * MapIterator&lt;String, Integer&gt; it = map.mapIterator();
     * while (it.hasNext()) {
     *     String key = it.next();
     *     Integer value = it.getValue();
     *     it.setValue(value + 1);
     * }
     * </pre>
     *
     * @return a map iterator
     */
    MapIterator<K, V> mapIterator();
}
