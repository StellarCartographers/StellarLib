package space.tscg.api.domain;

import java.net.URI;

import okhttp3.HttpUrl;
import space.tscg.common.domain.Domain;
import space.tscg.common.domain.DomainEndpoint;

public interface APIEndpoint
{
    default String getTemplate()
    {
        return "https://%s%s";
    }

    String getTag();

    DomainEndpoint[] ofTag(String tag);

    HttpUrl toHttpUrl();

    HttpUrl toHttpUrl(Domain domain);

    URI toUri();

    URI toUri(Domain domain);;
}
