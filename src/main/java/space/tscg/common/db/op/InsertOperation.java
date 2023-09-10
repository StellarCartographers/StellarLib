package space.tscg.common.db.op;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InsertOperation implements Op<InsertOperation>
{
    @JsonProperty("generated_keys")
    private final List<String> generatedKeys = new ArrayList<>();
    private final Changes      changes       = new Changes();
    private int                inserted, replaced, unchanged, deleted, skipped, errors;
    @JsonProperty("first_error")
    private String             firstError;
    private String             warnings;
    
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
