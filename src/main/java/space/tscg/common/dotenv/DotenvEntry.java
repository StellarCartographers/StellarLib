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
 * A key value pair representing an environment variable and its value
 */
public class DotenvEntry {

    private final String key;
    private final String value;

    /**
     * Creates a new dotenv entry using the provided key and value
     * @param key the dotenv entry name
     * @param value the dotenv entry value
     */
    public DotenvEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key for the {@link DotenvEntry}
     * @return the key for the {@link DotenvEntry}
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the value for the {@link DotenvEntry}
     * @return the value for the {@link DotenvEntry}
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key+"="+value;
    }
}

