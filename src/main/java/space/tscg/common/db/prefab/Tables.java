package space.tscg.common.db.prefab;

public enum Tables
{
    METRICS,
    CARRIERS,
    MEMBERS,
    AUTH,
    CHANNELS
    ;
    
    @Override
    public String toString()
    {
        return super.toString().toLowerCase();
    }
}
