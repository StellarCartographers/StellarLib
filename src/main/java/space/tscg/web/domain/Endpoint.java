/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.web.domain;

import okhttp3.HttpUrl;

import java.net.URI;

import space.tscg.api.domain.APIEndpoint;

public enum Endpoint implements APIEndpoint
{
    API_CARRIER("/v1/carrier"),
    OAUTH_CALLBACK("/v1/oauth/callback"),
    OAUTH_AUTHLINK("/v1/oauth/authorizationlink"),
    CAPI_CARRIER("/v1/capi/{}/carrier"),
    CAPI_PROFILE("/v1/capi/{}/profile"),;

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

    public HttpUrl toHttpUrl(String token)
    {
        String tokenized = endpoint.replaceAll("\\{\\}", token);
        HttpUrl httpurl = HttpUrl.parse(APIEndpoint.super.getTemplate().formatted(getDomain(), tokenized));
        setDomain(null);
        return httpurl;
    }

    @Override
    public HttpUrl toHttpUrl()
    {
        HttpUrl httpurl = HttpUrl.parse(APIEndpoint.super.getTemplate().formatted(getDomain(), endpoint));
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
