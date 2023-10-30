package space.tscg.database.operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;

import lombok.Data;
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
        TypeReference<T> reference = new TypeReference<>() { };
        return StellarMapper.get().asOptional(newValue, reference).orElseGet(null);
    }

    public <T> Optional<T> getNewValueAsType(Class<T> clazz)
    {
        return StellarMapper.get().asOptional(newValue, clazz);
    }
}