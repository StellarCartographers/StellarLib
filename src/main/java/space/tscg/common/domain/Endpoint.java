package space.tscg.common.domain;

import java.net.URI;

import okhttp3.HttpUrl;
import space.tscg.api.domain.APIEndpoint;

public enum Endpoint implements APIEndpoint
{
    API_CARRIER("/api/carrier"),
    OAUTH_CALLBACK("/api/oauth/callback"),
    OAUTH_AUTHLINK("/api/oauth/authorizationlink"),
    CAPI_CARRIER("/internal/capi/carrier"),
    CAPI_PROFILE("/internal/capi/profile"),
    ;

    private Domain domain;
    private String endpoint;

    Endpoint(String endpoint)
    {
        this.endpoint = endpoint;
    }
    
    Endpoint withDomain(Domain domain)
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
