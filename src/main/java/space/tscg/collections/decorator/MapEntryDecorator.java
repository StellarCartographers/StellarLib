/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.decorator;

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
     * @param  entry
     *                                  the {@code Map.Entry} to decorate, must not be null
     * 
     * @throws NullPointerException
     *                                  if the collection is null
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
        if (object == this)
        {
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
