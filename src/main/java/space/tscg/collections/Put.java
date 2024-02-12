/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections;

import java.util.Map;

public interface Put<K, V>
{
    /**
     * @see Map#clear()
     */
    void clear();

    /**
     * Note that the return type is Object, rather than V as in the Map interface.
     * See the class Javadoc for further info.
     *
     * @param  key
     *                   key with which the specified value is to be associated
     * @param  value
     *                   value to be associated with the specified key
     * 
     * @return       the previous value associated with {@code key}, or
     *               {@code null} if there was no mapping for {@code key}.
     *               (A {@code null} return can also indicate that the map
     *               previously associated {@code null} with {@code key},
     *               if the implementation supports {@code null} values.)
     * 
     * @see          Map#put(Object, Object)
     */
    Object put(K key, V value);

    /**
     * @param t
     *              mappings to be stored in this map
     * 
     * @see     Map#putAll(Map)
     */
    void putAll(Map<? extends K, ? extends V> t);
}
