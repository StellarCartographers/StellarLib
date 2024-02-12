/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import space.tscg.collections.Unalterable;
import space.tscg.collections.UnalterableCollection;
import space.tscg.collections.decorator.MapDecorator;
import space.tscg.collections.iterator.EntrySetMapIterator;
import space.tscg.collections.iterator.IterableMap;
import space.tscg.collections.iterator.MapIterator;
import space.tscg.collections.iterator.UnalterableMapIterator;
import space.tscg.collections.set.UnalterableSet;

public final class UnalterableMap<K, V> extends MapDecorator<K, V> implements Unalterable, Serializable
{
    private static final long serialVersionUID = -5901737424365569264L;

    /**
     * Factory method to create an unmodifiable map.
     *
     * @param  <K>
     *                                  the key type
     * @param  <V>
     *                                  the value type
     * @param  map
     *                                  the map to decorate, must not be null
     * 
     * @return                      a new unmodifiable map
     * 
     * @throws NullPointerException
     *                                  if map is null
     * 
     * @since                       4.0
     */
    public static <K, V> UnalterableMap<K, V> unalterableMap(final Map<? extends K, ? extends V> map)
    {
        if (map instanceof Unalterable)
        {
            @SuppressWarnings("unchecked") // safe to upcast
            final Map<K, V> tmpMap = (Map<K, V>) map;
            return (UnalterableMap<K, V>) tmpMap;
        }
        return new UnalterableMap<>(map);
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param  map
     *                                  the map to decorate, must not be null
     * 
     * @throws NullPointerException
     *                                  if map is null
     */
    @SuppressWarnings("unchecked") // safe to upcast
    private UnalterableMap(final Map<? extends K, ? extends V> map)
    {
        super((Map<K, V>) map);
    }

    /**
     * Write the map out using a custom routine.
     *
     * @param  out
     *                         the output stream
     * 
     * @throws IOException
     *                         if an error occurs while writing to the stream
     * 
     * @since              3.1
     */
    private void writeObject(final ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
        out.writeObject(map);
    }

    /**
     * Read the map in using a custom routine.
     *
     * @param  in
     *                                    the input stream
     * 
     * @throws IOException
     *                                    if an error occurs while reading from the stream
     * @throws ClassNotFoundException
     *                                    if an object read from the stream can not be loaded
     * 
     * @since                         3.1
     */
    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        map = (Map<K, V>) in.readObject();
    }

    @Override
    public void clear()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public V put(final K key, final V value)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> mapToCopy)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(final Object key)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public MapIterator<K, V> mapIterator()
    {
        if (map instanceof IterableMap)
        {
            final MapIterator<K, V> it = ((IterableMap<K, V>) map).mapIterator();
            return UnalterableMapIterator.unalterableMapIterator(it);
        }
        final MapIterator<K, V> it = new EntrySetMapIterator<>(map);
        return UnalterableMapIterator.unalterableMapIterator(it);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet()
    {
        final Set<Map.Entry<K, V>> set = super.entrySet();
        return UnalterableEntrySet.unalterableEntrySet(set);
    }

    @Override
    public Set<K> keySet()
    {
        final Set<K> set = super.keySet();
        return UnalterableSet.unalterableSet(set);
    }

    @Override
    public Collection<V> values()
    {
        final Collection<V> coll = super.values();
        return UnalterableCollection.unalterableCollection(coll);
    }

    public static <K, V> Builder<K, V> Builder()
    {
        return new Builder<K, V>();
    }

    public static class Builder<K, V> implements space.tscg.collections.Builder<UnalterableMap<K, V>>
    {
        private Map<K, V> builderMap = new HashMap<>();

        public Builder<K, V> add(K k, V v)
        {
            this.builderMap.put(k, v);
            return this;
        }

        @Override
        public UnalterableMap<K, V> build()
        {
            return (UnalterableMap<K, V>) UnalterableMap.unalterableMap(builderMap);
        }
    }
}
