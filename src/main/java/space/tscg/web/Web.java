/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.web;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import space.tscg.web.OkCli.HttpResponse;

public enum Web
{
    GET,
    POST,
    DELETE,
    PATCH;

    Request.Builder reqBuilder = new Request.Builder();

    public Web header(String key, String value)
    {
        reqBuilder.addHeader(key, value);
        return this;
    }

    public Web headers(String... keyValuePairs)
    {
        if ((keyValuePairs.length % 2) == 0)
            headers(Headers.of(keyValuePairs));
        return this;
    }

    public Web headers(Headers headers)
    {
        reqBuilder.headers(headers);
        return this;
    }

    public HttpResponse call(String url, RequestBody body)
    {
        return call(HttpUrl.parse(url), body);
    }

    public HttpResponse call(HttpUrl url, RequestBody body)
    {
        switch (this) {
            case POST -> reqBuilder.post(body);
            case PATCH -> reqBuilder.patch(body);
            case DELETE -> reqBuilder.delete(body);
            case GET -> call(url);
        }
        Request r = reqBuilder.url(url).build();
        try (Response response = OkCli.CLIENT.newCall(r).execute())
        {
            reqBuilder = new Request.Builder();
            return HttpResponse.from(response);
        } catch (IOException e)
        {
            reqBuilder = new Request.Builder();
            return HttpResponse.from(new Response.Builder().message(e.getMessage()).code(500).build());
        }
    }

    public HttpResponse call(String url)
    {
        return call(HttpUrl.parse(url));
    }

    public HttpResponse call(HttpUrl url)
    {
        switch (this) {
            case POST -> throw new IllegalArgumentException(this.name() + " Requires a RequestBody!");
            case PATCH -> throw new IllegalArgumentException(this.name() + " Requires a RequestBody!");
            case DELETE -> reqBuilder.delete();
            default -> reqBuilder.get();
        }
        Request r = reqBuilder.url(url).build();
        try (Response response = OkCli.CLIENT.newCall(r).execute())
        {
            reqBuilder = new Request.Builder();
            return HttpResponse.from(response);
        } catch (IOException e)
        {
            reqBuilder = new Request.Builder();
            return HttpResponse.from(new Response.Builder().message(e.getMessage()).code(500).build());
        }
    }
}
