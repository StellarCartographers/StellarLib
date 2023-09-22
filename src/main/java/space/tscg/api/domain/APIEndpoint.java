package space.tscg.api.domain;

import java.net.URI;

import okhttp3.HttpUrl;
import space.tscg.common.domain.Domain;

public interface APIEndpoint
{
    default String getTemplate()
    {
        return "%s%s";
    }

    HttpUrl toHttpUrl();

    HttpUrl toHttpUrl(Domain domain);

    URI toUri();

    URI toUri(Domain domain);;
}
