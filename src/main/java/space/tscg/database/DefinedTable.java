package space.tscg.database;

public enum DefinedTable
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
