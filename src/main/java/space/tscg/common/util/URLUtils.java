package space.tscg.common.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import lombok.experimental.UtilityClass;
import space.tscg.common.Standards;

@UtilityClass
public class URLUtils
{
    /**
     * Gets the base part (protocol, host, port and path) of the specified
     * URL.
     *
     * @param url The URL. May be {@code null}.
     *
     * @return The base part of the URL, {@code null} if the original URL 
     *         is {@code null} or doesn't specify a protocol.
     */
    public static URL getBaseURL(final URL url)
    {
        if (url == null)
            return null;

        try
        {
            return new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getPath());

        } catch (MalformedURLException e)
        {
            return null;
        }
    }

    /**
     * Performs {@code application/x-www-form-urlencoded} encoding on the
     * specified parameter keys and values.
     *
     * @param params A map of the parameters. May be empty or {@code null}.
     *
     * @return The encoded parameters, {@code null} if not specified.
     */
    public static Map<String, List<String>> urlEncodeParameters(final Map<String, List<String>> params)
    {
        if (MapUtils.isEmpty(params))
        {
            return params;
        }

        Map<String, List<String>> out = new LinkedHashMap<>(); // preserve order

        for (Map.Entry<String, List<String>> entry : params.entrySet())
        {
            String newKey = entry.getKey() != null ? URLEncoder.encode(entry.getKey(), Standards.UTF_8_CHARSET) : null;

            List<String> newValues;

            if (entry.getValue() != null)
            {
                newValues = new LinkedList<>();

                for (String value : entry.getValue())
                {
                    if (value != null)
                    {
                        newValues.add(URLEncoder.encode(value, Standards.UTF_8_CHARSET));
                    } else
                    {
                        newValues.add(null); // preserve null values
                    }
                }
            } else
            {
                newValues = null;
            }

            out.put(newKey, newValues);
        }

        return out;
    }

    /**
     * Serialises the specified map of parameters into a URL query string. 
     * The parameter keys and values are 
     * {@code application/x-www-form-urlencoded} encoded.
     *
     * <p>Note that the '?' character preceding the query string in GET
     * requests is not included in the returned string.
     *
     * <p>Example query string:
     *
     * <pre>
     * response_type=code
     * &amp;client_id=s6BhdRkqt3
     * &amp;state=xyz
     * &amp;redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb
     * </pre>
     *
     * <p>The opposite method is {@link #parseParameters}.
     *
     * @param params A map of the URL query parameters. May be empty or
     *               {@code null}.
     *
     * @return The serialised URL query string, empty if no parameters.
     */
    public static String serializeParameters(final Map<String, List<String>> params)
    {
        if ((params == null) || params.isEmpty())
            return "";

        Map<String, List<String>> encodedParams = urlEncodeParameters(params);

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, List<String>> entry : encodedParams.entrySet())
        {
            if ((entry.getKey() == null) || (entry.getValue() == null))
                continue;

            for (String value : entry.getValue())
            {
                if (value == null)
                {
                    value = "";
                }

                if (sb.length() > 0)
                    sb.append('&');

                sb.append(entry.getKey());
                sb.append('=');
                sb.append(value);
            }
        }

        return sb.toString();
    }

    /**
     * Serialises the specified map of parameters into a URL query string.
     * Supports multiple key / value pairs that have the same key. The
     * parameter keys and values are
     * {@code application/x-www-form-urlencoded} encoded.
     *
     * <p>Note that the '?' character preceding the query string in GET
     * requests is not included in the returned string.
     *
     * <p>Example query string:
     *
     * <pre>
     * response_type=code
     * &amp;client_id=s6BhdRkqt3
     * &amp;state=xyz
     * &amp;redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb
     * </pre>
     *
     * <p>The opposite method is {@link #parseParameters}.
     *
     * @param params A map of the URL query parameters. May be empty or
     *               {@code null}.
     *
     * @return The serialised URL query string, empty if no parameters.
     */
    public static String serializeParametersAlt(final Map<String, String[]> params)
    {
        if (params == null)
        {
            return serializeParameters(null);
        }

        Map<String, List<String>> out = new HashMap<>();

        for (Map.Entry<String, String[]> entry : params.entrySet())
        {
            if (entry.getValue() == null)
            {
                out.put(entry.getKey(), null);
            } else
            {
                out.put(entry.getKey(), Arrays.asList(entry.getValue()));
            }
        }

        return serializeParameters(out);
    }

    /**
     * Parses the specified URL query string into a parameter map. If a 
     * parameter has multiple values only the first one will be saved. The
     * parameter keys and values are 
     * {@code application/x-www-form-urlencoded} decoded.
     *
     * <p>Note that the '?' character preceding the query string in GET
     * requests must not be included.
     *
     * <p>Example query string:
     *
     * <pre>
     * response_type=code
     * &amp;client_id=s6BhdRkqt3
     * &amp;state=xyz
     * &amp;redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb
     * </pre>
     *
     * <p>The opposite method {@link #serializeParameters}.
     *
     * @param query The URL query string to parse. May be {@code null}.
     *
     * @return A map of the URL query parameters, empty if none are found.
     */
    public static Map<String, List<String>> parseParameters(final String query)
    {
        Map<String, List<String>> params = new HashMap<>();

        if (StringUtils.isBlank(query))
        {
            return params; // empty map
        }

        StringTokenizer st = new StringTokenizer(query.trim(), "&");

        while (st.hasMoreTokens())
        {
            String param = st.nextToken();

            String[] pair = param.split("=", 2); // Split around the first '=', see issue #169

            String key = URLDecoder.decode(pair[0], Standards.UTF_8_CHARSET);

            String value = pair.length > 1 ? URLDecoder.decode(pair[1], Standards.UTF_8_CHARSET) : "";

            if (params.containsKey(key))
            {
                // Append value
                List<String> updatedValueList = new LinkedList<>(params.get(key));
                updatedValueList.add(value);
                params.put(key, Collections.unmodifiableList(updatedValueList));
            } else
            {
                params.put(key, Collections.singletonList(value));
            }
        }

        return params;
    }
}
