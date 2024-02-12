/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections;

import java.util.Collection;
import java.util.Set;

public interface Get<K, V>
{
    /**
     * @param  key
     *                 key whose presence in this map is to be tested
     * 
     * @return     {@code true} if this map contains a mapping for the specified
     *             key
     * 
     * @see        java.util.Map#containsKey(Object)
     */
    boolean containsKey(Object key);

    /**
     * @param  value
     *                   value whose presence in this map is to be tested
     * 
     * @return       {@code true} if this map maps one or more keys to the
     *               specified value
     * 
     * @see          java.util.Map#containsValue(Object)
     */
    boolean containsValue(Object value);

    /**
     * @return a set view of the mappings contained in this map
     * 
     * @see    java.util.Map#entrySet()
     */
    Set<java.util.Map.Entry<K, V>> entrySet();

    /**
     * @param  key
     *                 the key whose associated value is to be returned
     * 
     * @return     the value to which the specified key is mapped, or
     *             {@code null} if this map contains no mapping for the key
     * 
     * @see        java.util.Map#get(Object)
     */
    V get(Object key);

    /**
     * @param  key
     *                 key whose mapping is to be removed from the map
     * 
     * @return     the previous value associated with {@code key}, or
     *             {@code null} if there was no mapping for {@code key}.
     * 
     * @see        java.util.Map#remove(Object)
     */
    V remove(Object key);

    /**
     * @return {@code true} if this map contains no key-value mappings
     * 
     * @see    java.util.Map#isEmpty()
     */
    boolean isEmpty();

    /**
     * @return a set view of the keys contained in this map
     * 
     * @see    java.util.Map#keySet()
     */
    Set<K> keySet();

    /**
     * @return the number of key-value mappings in this map
     * 
     * @see    java.util.Map#size()
     */
    int size();

    /**
     * @return a collection view of the values contained in this map
     * 
     * @see    java.util.Map#values()
     */
    Collection<V> values();
}
