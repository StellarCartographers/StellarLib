package space.tscg.common.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;

public interface ManagedObject {

    @JsonIgnore
    <T> TypeReference<T> getReference();

    String getId();

    @JsonIgnore
    String getTableName();
}
