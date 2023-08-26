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
package space.tscg.common.db.op;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Operation
{
    @JsonProperty("generated_keys")
    private final List<String> generatedKeys = new ArrayList<>();

    private int inserted, replaced, unchanged, deleted, skipped, errors;

    @JsonProperty("first_error")
    private String firstError;

    private String warnings;

    /**
     * List of GeneratedKeys (if any) from the Operation
     *
     * @return  {@literal List<String>} 
     */
    public List<String> getGeneratedKeys()
    {
        return generatedKeys;
    }

    /**
     * Number of inserted objects that occoured in the operation
     *
     * @return Number of inserts
     */
    public int getInserted()
    {
        return inserted;
    }

    /**
     * Number of repalced objects that occoured in the operation
     *
     * @return Number of repalced
     */
    public int getReplaced()
    {
        return replaced;
    }

    /**
     * Number of unchanged objects that occoured in the operation
     *
     * @return Number of unchanged
     */
    public int getUnchanged()
    {
        return unchanged;
    }

    /**
     * Number of deleted objects that occoured in the operation
     *
     * @return Number of deleted
     */
    public int getDeleted()
    {
        return deleted;
    }

    /**
     * Number of objects skipped during the operation
     *
     * @return Number of skips
     */
    public int getSkipped()
    {
        return skipped;
    }

    /**
     * Number of errors objects that occoured in the operation
     *
     * @return Number of errors
     */
    public int getErrors()
    {
        return errors;
    }

    /**
     * The first error message from an operation
     *
     * @return The first error message
     */
    public String getFirstError()
    {
        return firstError;
    }

    /**
     * Number of warnings that occoured in the operation
     *
     * @return Number of warnings
     */
    public String getWarnings()
    {
        return warnings;
    }
}
