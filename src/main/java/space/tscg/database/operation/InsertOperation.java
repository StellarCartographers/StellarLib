package space.tscg.database.operation;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.Data;
import panda.std.Result;
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
    
    public <T extends DbEntity> @NotNull Result<T, JsonProcessingException> getNewEntity()
    {
        return Result.supplyThrowing(JsonProcessingException.class, () -> get().getChanges().getFirst().mapNewValue());
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
