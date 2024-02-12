/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.iterator;

import java.util.Iterator;

public interface ResettableIterator<E> extends Iterator<E>
{
    /**
     * Resets the iterator back to the position at which the iterator
     * was created.
     */
    void reset();
}
