package space.tscg.database.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeleteOperation implements Operation<DeleteOperation>
{
    private int           deleted, skipped, errors;
    @JsonProperty("first_error")
    private String        firstError;
    private final int     inserted  = 0;
    private final int     replaced  = 0;
    private final int     unchanged = 0;
    private final Changes changes   = new Changes();

    @Override
    public boolean operationSucceded()
    {
        return (deleted > 0) && (skipped == 0) && (errors == 0);
    }

    @Override
    public DeleteOperation get()
    {
        return this;
    }
}
