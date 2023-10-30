package space.tscg.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;

public final class URLParser
{
    final String          regex             = "(?<scheme>(?:https?:\\/\\/?)+(?:www(?!.))?)?(?<host>[a-zA-Z0-9@:._-]{2,256}\\.[a-z0-9]{2,63})(?:\\:(?<port>[0-9]{1,5}))?(?<path>(?:\\/)[-a-zA-Z0-9\\/_]+)?(?<params>[-a-zA-Z0-9?%#+()=&]+)?";
    final String          localhostRegex    = "(?<host>localhost)(?:\\:(?<port>[0-9]{1,5}))?";
    private final Pattern URL_PATTERN       = Pattern.compile(regex);
    private final Pattern LOCALHOST_PATTERN = Pattern.compile(localhostRegex);
    
    private Matcher localhostMatcher;
    
    private final boolean matchFound;
    private String        fullMatch;
    @Getter
    private String        scheme;
    @Getter
    private String        host;
    @Getter
    private String        port;
    @Getter
    private String        path;
    @Getter
    private String        params;

    public URLParser(String url)
    {
        if(this.localhostMatching(url))
        {
            this.matchFound = true;
            this.scheme = "http://";
            this.host = "localhost";
            this.port = localhostMatcher.group("port");
            this.fullMatch = localhostMatcher.group();
        } else {
            Matcher matcher = URL_PATTERN.matcher(url);
            this.matchFound = matcher.find();
            this.scheme = matcher.group("scheme");
            this.host = matcher.group("host");
            this.port = matcher.group("port");
            this.path = matcher.group("path");
            this.params = matcher.group("params");
            this.fullMatch = matcher.group();
        }
    }

    private boolean localhostMatching(String url)
    {
        this.localhostMatcher = LOCALHOST_PATTERN.matcher(url);
        return localhostMatcher.find();
    }

    public String getFullMatch()
    {
        return this.fullMatch;
    }

    public boolean isValid()
    {
        return matchFound;
    }

    public boolean hasScheme()
    {
        return (scheme != null);
    }

    public boolean hasHost()
    {
        return (host != null);
    }

    public boolean hasPort()
    {
        return (port != null);
    }

    public boolean hasPath()
    {
        return (path != null);
    }

    public boolean hasParams()
    {
        return (params != null);
    }
    
    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append(this.scheme)
            .append(this.host)
            .append(this.port)
            .append(this.path)
            .append(this.params)
            .append(this.fullMatch).build();
    }
}
