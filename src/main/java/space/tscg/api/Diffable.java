/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.api;

import space.tscg.collections.DiffMap;

/**
 * Classes that inherit Diffable can have another instance of their type
 * passed in to check for the differences between the two
 *
 * @param <T>
 *                This must be the class thats implementing this interface
 */
public interface Diffable<T>
{
    /**
     * Generates a Diff style UpdatedValues class that acts as a Map only
     * containing fields or ojects that changed during an operation.
     *
     * @param  other
     *                   The same type to compare too
     * 
     * @return       an UpdatedValues for this type
     */
    DiffMap diff(T other);
}
