/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections;

public interface KeyValue<K, V>
{
    /**
     * Gets the key from the pair.
     *
     * @return the key
     */
    K getKey();

    /**
     * Gets the value from the pair.
     *
     * @return the value
     */
    V getValue();
}
