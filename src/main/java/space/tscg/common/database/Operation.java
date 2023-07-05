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
