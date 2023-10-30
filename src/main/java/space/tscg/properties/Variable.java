package space.tscg.properties;

public final class Variable
{
    private final String value;

    public Variable(Object value)
    {
        this.value = String.valueOf(value);
    }
    
    public Variable(String value)
    {
        this.value = value;
    }

    public String asString()
    {
        return value;
    }

    public int asInt()
    {
        return Integer.valueOf(value);
    }

    public boolean asBoolean()
    {
        return Boolean.valueOf(value);
    }
    
    @Override
    public String toString()
    {
        return asString();
    }
}
