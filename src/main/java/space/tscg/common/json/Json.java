package space.tscg.common.json;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class Json
{
    static final PrettyPrinter PRINTER = new DefaultPrettyPrinter()
        .withArrayIndenter(new DefaultIndenter("    ", DefaultIndenter.SYS_LF));
    
    static ObjectMapper MAPPER = new ObjectMapper()
        .setSerializationInclusion(Include.NON_NULL)
        .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
        .enable(SerializationFeature.INDENT_OUTPUT)
        .disable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    public static String prettyString(Object object)
    {
        try
        {
            return Json.MAPPER.setDefaultPrettyPrinter(PRINTER).writeValueAsString(object);
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "{ \"error\": \"Json Processing Error\" }";
        }
    }
    
    public static String string(Object object)
    {
        try
        {
            return Json.MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "{ \"error\": \"Json Processing Error\" }";
        }
    }

    public static <T> Optional<T> map(String jsonString, Class<T> clzz)
    {
        try
        {
            return Optional.of(Json.MAPPER.readValue(jsonString, clzz));
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
