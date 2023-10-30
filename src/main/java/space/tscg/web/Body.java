package space.tscg.web;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public final class Body
{
    public static RequestBody json(Object obj)
    {
        return RequestBody.create(obj.toString(), MediaType.get("application/json; charset=utf-8"));
    }
}
