package space.tscg.common.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Json
{
    private static final ObjectMapper MAPPER = new ObjectMapper().setSerializationInclusion(Include.NON_NULL).setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

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
