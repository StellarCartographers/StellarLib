/**
 * Copyright (C) 2023  The Stellar Cartographers' Guild
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package space.tscg.api;

import space.tscg.common.UpdatedValues;

/**
 * Classes that inherit Diffable can have another instance of their type
 * passed in to check for the differences between the two
 *
 * @param <T> This must be the class thats implementing this interface
 */
public interface Diffable<T> {
    /**
     * Generates a Diff style UpdatedValues class that acts as a Map only
     * containing fields or ojects that changed during an operation.
     *
     * @param other The same type to compare too
     * @return an UpdatedValues for this type
     */
    UpdatedValues diff(T other);
}
