package space.tscg.common.db.op;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.rethinkdb.RethinkDB;

import lombok.Data;
import space.tscg.api.database.DbEntity;
import space.tscg.common.json.Json;

@Data
public class Change
{
    @JsonProperty("old_val")
    private Map<String, Object> oldValue = new HashMap<>();
    @JsonProperty("new_val")
    private Map<String, Object> newValue = new HashMap<>();
    
    public <T extends DbEntity> T mapNewValue() throws JsonProcessingException
    {
        TypeReference<T> reference = new TypeReference<>() { };
        return RethinkDB.getResultMapper().readValue(Json.string(newValue), reference);
    }

    public <T> Optional<T> getNewValueAsType(Class<T> clazz)
    {
        var mapper = RethinkDB.getResultMapper();
        try
        {
            return Optional.ofNullable(mapper.readValue(Json.string(newValue), clazz));
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}