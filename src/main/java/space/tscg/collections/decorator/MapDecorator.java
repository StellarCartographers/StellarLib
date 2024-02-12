/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.decorator;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import space.tscg.collections.map.AbstractIterableMap;

public abstract class MapDecorator<K, V> extends AbstractIterableMap<K, V>
{
    /** The map to decorate */
    protected transient Map<K, V> map;

    /**
     * Constructor only used in deserialization, do not use otherwise.
     */
    protected MapDecorator()
    {
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param  map
     *                                  the map to decorate, must not be null
     * 
     * @throws NullPointerException
     *                                  if the map is null
     */
    protected MapDecorator(final Map<K, V> map)
    {
        this.map = Objects.requireNonNull(map, "map");
    }

    /**
     * Gets the map being decorated.
     *
     * @return the decorated map
     */
    protected Map<K, V> decorated()
    {
        return map;
    }

    @Override
    public void clear()
    {
        decorated().clear();
    }

    @Override
    public boolean containsKey(final Object key)
    {
        return decorated().containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value)
    {
        return decorated().containsValue(value);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet()
    {
        return decorated().entrySet();
    }

    @Override
    public V get(final Object key)
    {
        return decorated().get(key);
    }

    @Override
    public boolean isEmpty()
    {
        return decorated().isEmpty();
    }

    @Override
    public Set<K> keySet()
    {
        return decorated().keySet();
    }

    @Override
    public V put(final K key, final V value)
    {
        return decorated().put(key, value);
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> mapToCopy)
    {
        decorated().putAll(mapToCopy);
    }

    @Override
    public V remove(final Object key)
    {
        return decorated().remove(key);
    }

    @Override
    public int size()
    {
        return decorated().size();
    }

    @Override
    public Collection<V> values()
    {
        return decorated().values();
    }

    @Override
    public boolean equals(final Object object)
    {
        if (object == this)
        {
            return true;
        }
        return decorated().equals(object);
    }

    @Override
    public int hashCode()
    {
        return decorated().hashCode();
    }

    @Override
    public String toString()
    {
        return decorated().toString();
    }
}
