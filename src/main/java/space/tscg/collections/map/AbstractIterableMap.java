package space.tscg.collections.map;

import space.tscg.collections.iterator.IterableMap;
import space.tscg.collections.iterator.MapIterator;

public abstract class AbstractIterableMap<K, V> implements IterableMap<K, V>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public MapIterator<K, V> mapIterator()
    {
        return new EntrySetToMapIteratorAdapter<>(entrySet());
    }
}
