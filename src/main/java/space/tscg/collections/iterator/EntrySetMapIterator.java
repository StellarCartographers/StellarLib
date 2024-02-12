/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.iterator;

import java.util.Iterator;
import java.util.Map;

public class EntrySetMapIterator<K, V> implements MapIterator<K, V>, ResettableIterator<K>
{
    private final Map<K, V>           map;
    private Iterator<Map.Entry<K, V>> iterator;
    private Map.Entry<K, V>           last;
    private boolean                   canRemove;

    /**
     * Constructor.
     *
     * @param map
     *                the map to iterate over
     */
    public EntrySetMapIterator(final Map<K, V> map)
    {
        this.map = map;
        this.iterator = map.entrySet().iterator();
    }

    /**
     * Checks to see if there are more entries still to be iterated.
     *
     * @return {@code true} if the iterator has more elements
     */
    @Override
    public boolean hasNext()
    {
        return iterator.hasNext();
    }

    /**
     * Gets the next <em>key</em> from the {@code Map}.
     *
     * @return                                  the next key in the iteration
     * 
     * @throws java.util.NoSuchElementException
     *                                              if the iteration is finished
     */
    @Override
    public K next()
    {
        last = iterator.next();
        canRemove = true;
        return last.getKey();
    }

    /**
     * Removes the last returned key from the underlying {@code Map}.
     * <p>
     * This method can be called once per call to {@code next()}.
     *
     * @throws UnsupportedOperationException
     *                                           if remove is not supported by the map
     * @throws IllegalStateException
     *                                           if {@code next()} has not yet been called
     * @throws IllegalStateException
     *                                           if {@code remove()} has already been called
     *                                           since the last call to {@code next()}
     */
    @Override
    public void remove()
    {
        if (!canRemove)
        {
            throw new IllegalStateException("Iterator remove() can only be called once after next()");
        }
        iterator.remove();
        last = null;
        canRemove = false;
    }

    /**
     * Gets the current key, which is the key returned by the last call
     * to {@code next()}.
     *
     * @return                       the current key
     * 
     * @throws IllegalStateException
     *                                   if {@code next()} has not yet been called
     */
    @Override
    public K getKey()
    {
        if (last == null)
        {
            throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
        }
        return last.getKey();
    }

    /**
     * Gets the current value, which is the value associated with the last key
     * returned by {@code next()}.
     *
     * @return                       the current value
     * 
     * @throws IllegalStateException
     *                                   if {@code next()} has not yet been called
     */
    @Override
    public V getValue()
    {
        if (last == null)
        {
            throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
        }
        return last.getValue();
    }

    /**
     * Sets the value associated with the current key.
     *
     * @param  value
     *                                           the new value
     * 
     * @return                               the previous value
     * 
     * @throws UnsupportedOperationException
     *                                           if setValue is not supported by the map
     * @throws IllegalStateException
     *                                           if {@code next()} has not yet been called
     * @throws IllegalStateException
     *                                           if {@code remove()} has been called since the
     *                                           last call to {@code next()}
     */
    @Override
    public V setValue(final V value)
    {
        if (last == null)
        {
            throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
        }
        return last.setValue(value);
    }

    /**
     * Resets the state of the iterator.
     */
    @Override
    public void reset()
    {
        iterator = map.entrySet().iterator();
        last = null;
        canRemove = false;
    }

    /**
     * Gets the iterator as a String.
     *
     * @return a string version of the iterator
     */
    @Override
    public String toString()
    {
        if (last != null)
        {
            return "MapIterator[" + getKey() + "=" + getValue() + "]";
        }
        return "MapIterator[]";
    }
}
