package space.tscg.common.db.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class DbEntity
{
    public abstract String getId();

    @JsonIgnore
    public abstract String getTableName();
}
