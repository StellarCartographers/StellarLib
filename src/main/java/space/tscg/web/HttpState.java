package space.tscg.web;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import io.javalin.http.HttpStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import space.tscg.api.JsonWrapper;
import space.tscg.collections.Data;
import space.tscg.misc.json.StellarMapper;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class HttpState implements JsonWrapper
{
    private final int    code;
    private final String state;
    @SuppressWarnings("rawtypes")
    private Data         data;

    public HttpStatus toHttpStatus()
    {
        return HttpStatus.forStatus(code);
    }

    public <T> HttpState withData(Data<T> data)
    {
        this.data = data;
        return this;
    }
    
    @Override
    public String toJson()
    {
        return StellarMapper.get().asOptionalToString(this).orElseThrow();
    }

    @UtilityClass
    public static final class HttpStateAdapter {
        
        public static class Serializer extends StdSerializer<HttpState> {

            private static final long serialVersionUID = 9115662983758042660L;

            protected Serializer()
            {
                super(HttpState.class);
            }

            @Override
            public void serialize(HttpState value, JsonGenerator gen, SerializerProvider provider) throws IOException
            {
            }
        }
        
        public static class Deserializer extends StdDeserializer<HttpState> {

            private static final long serialVersionUID = 5873054711350065553L;

            protected Deserializer()
            {
                super(HttpState.class);
            }

            @Override
            public HttpState deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException
            {
                return null;
            }
            
        }
    }
}
