/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections;

import space.tscg.collections.list.UnalterableList;
import space.tscg.collections.map.UnalterableMap;

/**
 * Interface representing any object that, once created, or built cannot be changed in any way
 */
public interface Unalterable
{
    // no methods to implement
    static <E> UnalterableList.Builder<E> listBuilder()
    {
        return UnalterableList.Builder();
    }

    static <K, V> UnalterableMap.Builder<K, V> mapBuilder()
    {
        return UnalterableMap.Builder();
    }
}
