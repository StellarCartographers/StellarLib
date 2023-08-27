package space.tscg.common.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Json
{
    public static final ObjectMapper MAPPER = new ObjectMapper()
        .setSerializationInclusion(Include.NON_NULL)
        .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    public static String string(Object object)
    {
        try
        {
            return Json.MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e)
        {
            return "{ \"error\": \"error\" }";
        }
    }
}
