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
package space.tscg.common.dotenv;

/**
 * Signals that dotenv exception of some sort has occurred.
 */
public class DotenvException extends RuntimeException
{
    private static final long serialVersionUID = 6550159518654821234L;

    /**
     * Create a dotenv runtime exception with the specified detail message
     * @param message the detail message
     */
    public DotenvException(String message)
    {
        super(message);
    }

    /**
     * Creates a dotenv runtime exception
     * @param cause the cause
     */
    public DotenvException(Throwable cause)
    {
        super(cause);
    }
}
