package space.tscg.collections.map;

import java.util.Map;
import java.util.Objects;

import space.tscg.collections.KeyValue;

public abstract class MapEntryDecorator<K, V> implements Map.Entry<K, V>, KeyValue<K, V>
{
    /** The {@code Map.Entry} to decorate */
    private final Map.Entry<K, V> entry;

    /**
     * Constructor that wraps (not copies).
     *
     * @param entry
     *            the {@code Map.Entry} to decorate, must not be null
     * 
     * @throws NullPointerException
     *             if the collection is null
     */
    public MapEntryDecorator(final Map.Entry<K, V> entry)
    {
        this.entry = Objects.requireNonNull(entry, "entry");
    }

    /**
     * Gets the map being decorated.
     *
     * @return the decorated map
     */
    protected Map.Entry<K, V> getMapEntry()
    {
        return entry;
    }

    @Override
    public K getKey()
    {
        return entry.getKey();
    }

    @Override
    public V getValue()
    {
        return entry.getValue();
    }

    @Override
    public V setValue(final V object)
    {
        return entry.setValue(object);
    }

    @Override
    public boolean equals(final Object object)
    {
        if (object == this) {
            return true;
        }
        return entry.equals(object);
    }

    @Override
    public int hashCode()
    {
        return entry.hashCode();
    }

    @Override
    public String toString()
    {
        return entry.toString();
    }
}
