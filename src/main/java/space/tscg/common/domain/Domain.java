package space.tscg.common.domain;

public class Domain
{
    private String domain;

    public static Domain of(String domain)
    {
        return new Domain(domain);
    }

    private Domain(String domain)
    {
        this.domain = domain;
    }

    public static final Domain getDefault()
    {
        return DefaultDomain.getDefault();
    }
    
    public String name()
    {
        return toString();
    }
    
    @Override
    public String toString()
    {
        return domain;
    }
    
    public Endpoint toEndpoint(Endpoint endpoint)
    {
        return endpoint.withDomain(this);
    }
}
