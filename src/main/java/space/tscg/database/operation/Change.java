/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.database.operation;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import space.tscg.api.database.DbEntity;
import space.tscg.misc.json.StellarMapper;

@Data
public class Change
{
    @JsonProperty("old_val")
    private Map<String, Object> oldValue = new HashMap<>();
    @JsonProperty("new_val")
    private Map<String, Object> newValue = new HashMap<>();

    public <T extends DbEntity> T mapNewValue()
    {
        TypeReference<T> reference = new TypeReference<>()
        {
        };
        return StellarMapper.get().asOptional(newValue, reference).orElseGet(null);
    }

    public <T> Optional<T> getNewValueAsType(Class<T> clazz)
    {
        return StellarMapper.get().asOptional(newValue, clazz);
    }
}
