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
package space.tscg.common.database;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class Operation
{
    @JsonProperty("generated_keys")
    private final List<String> generatedKeys = new ArrayList<>();
    private int                inserted, replaced, unchanged, deleted, skipped, errors;
    @JsonProperty("first_error")
    private String             firstError;
    private String             warnings;

    public static Operation Error(String message)
    {
        var o = new Operation();
        o.firstError = message;
        o.errors = 1;
        return o;
    }
}
