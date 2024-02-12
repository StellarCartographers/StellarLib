/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.database.operation;

import lombok.Data;
import panda.std.Result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;

import space.tscg.api.database.DbEntity;

@Data
public class InsertOperation implements Operation<InsertOperation>
{
    @JsonProperty("generated_keys")
    private final List<String> generatedKeys = new ArrayList<>();
    private final Changes      changes       = new Changes();
    private int                inserted, replaced, unchanged, deleted, skipped, errors;
    @JsonProperty("first_error")
    private String             firstError;
    private String             warnings;

    public <T extends DbEntity> Result<T, JsonProcessingException> getNewEntity()
    {
        return Result.supplyThrowing(JsonProcessingException.class, () -> get().changes().getFirst().mapNewValue());
    }

    @Override
    public InsertOperation get()
    {
        return this;
    }

    @Override
    public boolean operationSucceded()
    {
        return (inserted > 0) || (replaced > 0);
    }
}
