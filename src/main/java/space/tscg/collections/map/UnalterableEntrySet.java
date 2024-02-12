/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.map;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import space.tscg.collections.Unalterable;
import space.tscg.collections.decorator.IteratorDecorator;
import space.tscg.collections.decorator.MapEntryDecorator;
import space.tscg.collections.decorator.SetDecorator;

public final class UnalterableEntrySet<K, V> extends SetDecorator<Map.Entry<K, V>> implements Unalterable
{
    private static final long serialVersionUID = 7575967855282030390L;

    /**
     * Factory method to create an unmodifiable set of Map Entry objects.
     *
     * @param  <K>
     *                                  the key type
     * @param  <V>
     *                                  the value type
     * @param  set
     *                                  the set to decorate, must not be null
     * 
     * @return                      a new unmodifiable entry set
     * 
     * @throws NullPointerException
     *                                  if set is null
     * 
     * @since                       4.0
     */
    public static <K, V> Set<Map.Entry<K, V>> unalterableEntrySet(final Set<Map.Entry<K, V>> set)
    {
        if (set instanceof Unalterable)
        {
            return set;
        }
        return new UnalterableEntrySet<>(set);
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param  set
     *                                  the set to decorate, must not be null
     * 
     * @throws NullPointerException
     *                                  if set is null
     */
    private UnalterableEntrySet(final Set<Map.Entry<K, V>> set)
    {
        super(set);
    }

    @Override
    public boolean add(final Map.Entry<K, V> object)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final Collection<? extends Map.Entry<K, V>> coll)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(final Object object)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @since 4.4
     */
    @Override
    public boolean removeIf(final Predicate<? super Map.Entry<K, V>> filter)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(final Collection<?> coll)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(final Collection<?> coll)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator()
    {
        return new UnmodifiableEntrySetIterator(decorated().iterator());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] toArray()
    {
        final Object[] array = decorated().toArray();
        for (int i = 0; i < array.length; i++)
        {
            array[i] = new UnmodifiableEntry((Map.Entry<K, V>) array[i]);
        }
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(final T[] array)
    {
        Object[] result = array;
        if (array.length > 0)
        {
            // we must create a new array to handle multithreaded situations
            // where another thread could access data before we decorate it
            result = (Object[]) Array.newInstance(array.getClass().getComponentType(), 0);
        }
        result = decorated().toArray(result);
        for (int i = 0; i < result.length; i++)
        {
            result[i] = new UnmodifiableEntry((Map.Entry<K, V>) result[i]);
        }
        // check to see if result should be returned straight
        if (result.length > array.length)
        {
            return (T[]) result;
        }
        // copy back into input array to fulfill the method contract
        System.arraycopy(result, 0, array, 0, result.length);
        if (array.length > result.length)
        {
            array[result.length] = null;
        }
        return array;
    }

    /**
     * Implementation of an entry set iterator.
     */
    private class UnmodifiableEntrySetIterator extends IteratorDecorator<Map.Entry<K, V>>
    {
        protected UnmodifiableEntrySetIterator(final Iterator<Map.Entry<K, V>> iterator)
        {
            super(iterator);
        }

        @Override
        public Map.Entry<K, V> next()
        {
            return new UnmodifiableEntry(getIterator().next());
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Implementation of a map entry that is unmodifiable.
     */
    private class UnmodifiableEntry extends MapEntryDecorator<K, V>
    {
        protected UnmodifiableEntry(final Map.Entry<K, V> entry)
        {
            super(entry);
        }

        @Override
        public V setValue(final V obj)
        {
            throw new UnsupportedOperationException();
        }
    }
}
