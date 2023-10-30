package space.tscg.collections.iterator;

import java.util.Objects;

import space.tscg.collections.Unalterable;

public final class UnalterableMapIterator<K, V> implements MapIterator<K, V>, Unalterable
{
    /** The iterator being decorated */
    private final MapIterator<? extends K, ? extends V> iterator;

    /**
     * Decorates the specified iterator such that it cannot be modified.
     *
     * @param <K>
     *            the key type
     * @param <V>
     *            the value type
     * @param iterator
     *            the iterator to decorate
     * 
     * @return a new unmodifiable map iterator
     * 
     * @throws NullPointerException
     *             if the iterator is null
     */
    public static <K, V> MapIterator<K, V> unalterableMapIterator(final MapIterator<? extends K, ? extends V> iterator)
    {
        Objects.requireNonNull(iterator, "iterator");
        if (iterator instanceof Unalterable) {
            @SuppressWarnings("unchecked") // safe to upcast
            final MapIterator<K, V> tmpIterator = (MapIterator<K, V>) iterator;
            return tmpIterator;
        }
        return new UnalterableMapIterator<>(iterator);
    }

    /**
     * Constructor.
     *
     * @param iterator
     *            the iterator to decorate
     */
    private UnalterableMapIterator(final MapIterator<? extends K, ? extends V> iterator)
    {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext()
    {
        return iterator.hasNext();
    }

    @Override
    public K next()
    {
        return iterator.next();
    }

    @Override
    public K getKey()
    {
        return iterator.getKey();
    }

    @Override
    public V getValue()
    {
        return iterator.getValue();
    }

    @Override
    public V setValue(final V value)
    {
        throw new UnsupportedOperationException("setValue() is not supported");
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}
