package space.tscg.common.db.op;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Changes extends ArrayList<Change>
{
    private static final long serialVersionUID = 7624942674057465324L;

    @JsonIgnore
    public Change getFirst()
    {
        if (this.size() >= 1)
        {
            return this.get(0);
        }
        return new Change();
    }
}