package space.tscg.database.core;

import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DefaultRethinkMapper
{
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private ObjectMapper mapper;
    
    static {
        if(instance0().getMapper() == null)
            instance0().setMapper(new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setSerializationInclusion(Include.NON_NULL)
                .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            );
    }
    
    static ObjectMapper getDefault()
    {
        return instance0().getMapper();
    }
    
    public static void addConfigurations(Consumer<ObjectMapper> mapper)
    {
        mapper.accept(instance0().getMapper());
    }
    
    private volatile static DefaultRethinkMapper instance;

    private static DefaultRethinkMapper instance0()
    {
        if (instance == null)
        {
            synchronized (DefaultRethinkMapper.class)
            {
                if (instance == null)
                {
                    instance = new DefaultRethinkMapper();
                }
            }
        }
        return instance;
    }
}
