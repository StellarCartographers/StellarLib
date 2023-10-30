package space.tscg.collections.iterator;

import java.util.Iterator;

public interface MapIterator<K, V> extends Iterator<K>
{
    /**
     * Checks to see if there are more entries still to be iterated.
     *
     * @return {@code true} if the iterator has more elements
     */
    @Override
    boolean hasNext();

    /**
     * Gets the next <em>key</em> from the {@code Map}.
     *
     * @return the next key in the iteration
     * 
     * @throws java.util.NoSuchElementException
     *             if the iteration is finished
     */
    @Override
    K next();

    /**
     * Gets the current key, which is the key returned by the last call
     * to {@code next()}.
     *
     * @return the current key
     * 
     * @throws IllegalStateException
     *             if {@code next()} has not yet been called
     */
    K getKey();

    /**
     * Gets the current value, which is the value associated with the last key
     * returned by {@code next()}.
     *
     * @return the current value
     * 
     * @throws IllegalStateException
     *             if {@code next()} has not yet been called
     */
    V getValue();

    /**
     * Removes the last returned key from the underlying {@code Map} (optional operation).
     * <p>
     * This method can be called once per call to {@code next()}.
     *
     * @throws UnsupportedOperationException
     *             if remove is not supported by the map
     * @throws IllegalStateException
     *             if {@code next()} has not yet been called
     * @throws IllegalStateException
     *             if {@code remove()} has already been called
     *             since the last call to {@code next()}
     */
    @Override
    void remove();

    /**
     * Sets the value associated with the current key (optional operation).
     *
     * @param value
     *            the new value
     * 
     * @return the previous value
     * 
     * @throws UnsupportedOperationException
     *             if setValue is not supported by the map
     * @throws IllegalStateException
     *             if {@code next()} has not yet been called
     * @throws IllegalStateException
     *             if {@code remove()} has been called since the
     *             last call to {@code next()}
     */
    V setValue(V value);
}
