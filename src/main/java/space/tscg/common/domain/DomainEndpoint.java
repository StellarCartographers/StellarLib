package space.tscg.common.domain;

import java.net.URI;
import java.util.Arrays;

import okhttp3.HttpUrl;
import space.tscg.api.domain.APIEndpoint;

public enum DomainEndpoint implements APIEndpoint
{
    OAUTH_CALLBACK("/api/oauth/callback"),
    OAUTH_AUTHLINK("/api/oauth/authorizationlink"),
    CAPI_CARRIER("/api/capi/carrier"),
    CAPI_PROFILE("/api/capi/profile"),
    ;

    private Domain domain;
    private String endpoint;

    DomainEndpoint(String endpoint)
    {
        this.endpoint = endpoint;
    }
    
    DomainEndpoint withDomain(Domain domain)
    {
        setDomain(domain);
        return this;
    }
    
    private void setDomain(Domain domain)
    {
        this.domain = domain;
    }
    
    private Domain getDomain()
    {
        return domain == null ? Domain.getDefault() : domain;
    }
    
    public String getEndpoint()
    {
        return endpoint;
    }
    
    public String getBackend()
    {
        return endpoint.split("/")[0];
    }

    @Override
    public String getTag()
    {
        return name().toLowerCase().split("_")[0];
    }

    @Override
    public DomainEndpoint[] ofTag(String tag)
    {
        return Arrays.asList(DomainEndpoint.values()).stream().filter(de -> de.getTag().equals(tag)).toArray(DomainEndpoint[]::new);
    }

    @Override
    public HttpUrl toHttpUrl()
    {
        var httpurl = HttpUrl.parse(APIEndpoint.super.getTemplate().formatted(getDomain(), endpoint));
        setDomain(null);
        return httpurl;
    }

    @Override
    public URI toUri()
    {
        var uri = URI.create(APIEndpoint.super.getTemplate().formatted(getDomain(), endpoint));
        setDomain(null);
        return uri;
    }

    @Override
    public HttpUrl toHttpUrl(Domain domain)
    {
        setDomain(null);
        return HttpUrl.parse(APIEndpoint.super.getTemplate().formatted(domain, endpoint));
    }

    @Override
    public URI toUri(Domain domain)
    {
        setDomain(null);
        return URI.create(APIEndpoint.super.getTemplate().formatted(domain, endpoint));
    }
}
