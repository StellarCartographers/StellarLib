package space.tscg.web.domain;

import space.tscg.web.URLParser;

public class Domain
{
    private String scheme;
    private String port;
    private String host;
    
    public static Domain of(String domain)
    {
        URLParser parser = new URLParser(domain);
        if(parser.isValid())
            return new Domain(parser);
        throw new RuntimeException("Invalid domain passed to URLParser: " + domain);
    }
    
    private Domain(URLParser parser)
    {
        if(parser.hasScheme())
            this.scheme = parser.getScheme();
        else
            this.scheme = "https://";
        if(parser.hasPort())
            this.port = parser.getPort();
        this.host = parser.getHost();
    }

    public static final Domain getDefault()
    {
        return DefaultDomain.getDefault();
    }

    @Override
    public String toString()
    {
        return this.port != null ? "%s%s:%s".formatted(this.scheme, this.host, this.port) : "%s%s".formatted(this.scheme, this.host);
    }
    
    public Endpoint toEndpoint(Endpoint endpoint)
    {
        return endpoint.withDomain(this);
    }
}
