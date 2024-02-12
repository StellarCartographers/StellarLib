/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.iterator;

import space.tscg.collections.Get;

public interface IterableGet<K, V> extends Get<K, V>
{
    /**
     * Obtains a {@code MapIterator} over the map.
     * <p>
     * A map iterator is an efficient way of iterating over maps.
     * There is no need to access the entry set or use Map Entry objects.
     * 
     * <pre>
     * IterableMap&lt;String, Integer&gt; map = new HashedMap&lt;String, Integer&gt;();
     * MapIterator&lt;String, Integer&gt; it = map.mapIterator();
     * while (it.hasNext())
     * {
     *     String key = it.next();
     *     Integer value = it.getValue();
     *     it.setValue(value + 1);
     * }
     * </pre>
     *
     * @return a map iterator
     */
    MapIterator<K, V> mapIterator();
}
