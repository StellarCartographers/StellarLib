package space.tscg.collections.map;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import space.tscg.collections.iterator.MapIterator;
import space.tscg.collections.iterator.ResettableIterator;

public class EntrySetToMapIteratorAdapter<K, V> implements MapIterator<K, V>, ResettableIterator<K>
{
    /** The adapted Map entry Set. */
    final Set<Map.Entry<K, V>>          entrySet;

    /** The resettable iterator in use. */
    transient Iterator<Map.Entry<K, V>> iterator;

    /** The currently positioned Map entry. */
    transient Map.Entry<K, V>           entry;

    /**
     * Create a new EntrySetToMapIteratorAdapter.
     * 
     * @param entrySet
     *            the entrySet to adapt
     */
    public EntrySetToMapIteratorAdapter(final Set<Map.Entry<K, V>> entrySet)
    {
        this.entrySet = entrySet;
        reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public K getKey()
    {
        return current().getKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V getValue()
    {
        return current().getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V setValue(final V value)
    {
        return current().setValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext()
    {
        return iterator.hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public K next()
    {
        entry = iterator.next();
        return getKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void reset()
    {
        iterator = entrySet.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove()
    {
        iterator.remove();
        entry = null;
    }

    /**
     * Get the currently active entry.
     * 
     * @return Map.Entry&lt;K, V&gt;
     */
    protected synchronized Map.Entry<K, V> current()
    {
        if (entry == null) {
            throw new IllegalStateException();
        }
        return entry;
    }
}
