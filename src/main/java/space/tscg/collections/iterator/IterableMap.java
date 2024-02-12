/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.iterator;

import java.util.Map;

import space.tscg.collections.Put;

public interface IterableMap<K, V> extends Map<K, V>, Put<K, V>, IterableGet<K, V>
{
}
