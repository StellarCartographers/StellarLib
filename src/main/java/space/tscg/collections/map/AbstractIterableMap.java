/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.map;

import space.tscg.collections.iterator.IterableMap;
import space.tscg.collections.iterator.MapIterator;

public abstract class AbstractIterableMap<K, V> implements IterableMap<K, V>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public MapIterator<K, V> mapIterator()
    {
        return new EntrySetToMapIteratorAdapter<>(entrySet());
    }
}
