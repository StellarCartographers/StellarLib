package space.tscg.common.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import panda.std.Result;
import space.tscg.common.http.HttpError.HttpErrorSerialization;

@SuppressWarnings({"unchecked", "rawtypes"})
@JsonSerialize(using = HttpErrorSerialization.class)
public record HttpError(HttpState state)
{
    public <T> Result<T, HttpError> asResult()
    {
        return Result.error(this);
    }
    
    public static <T> Result<T, HttpError> unauthorized(Data data)
    {
        return Result.error(new HttpError(HttpState.UNAUTHORIZED.withData(data)));
    }

    public static <T> Result<T, HttpError> forbidden(Data data)
    {
        return Result.error(new HttpError(HttpState.FORBIDDEN.withData(data)));
    }

    public static <T> Result<T, HttpError> badRequest(Data data)
    {
        return Result.error(new HttpError(HttpState.BAD_REQUEST.withData(data)));
    }

    public static <T> Result<T, HttpError> notFound(Data data)
    {
        return Result.error(new HttpError(HttpState.NOT_FOUND.withData(data)));
    }

    public static <T> Result<T, HttpError> conflict(Data data)
    {
        return Result.error(new HttpError(HttpState.CONFLICT.withData(data)));
    }

    public static <T> Result<T, HttpError> internalServerError(Data data)
    {
        return Result.error(new HttpError(HttpState.INTERNAL_SERVER_ERROR.withData(data)));
    }

    public static <T> Result<T, HttpError> unauthorized()
    {
        return Result.error(new HttpError(HttpState.UNAUTHORIZED));
    }

    public static <T> Result<T, HttpError> forbidden()
    {
        return Result.error(new HttpError(HttpState.FORBIDDEN));
    }

    public static <T> Result<T, HttpError> badRequest()
    {
        return Result.error(new HttpError(HttpState.BAD_REQUEST));
    }

    public static <T> Result<T, HttpError> notFound()
    {
        return Result.error(new HttpError(HttpState.NOT_FOUND));
    }

    public static <T> Result<T, HttpError> conflict()
    {
        return Result.error(new HttpError(HttpState.CONFLICT));
    }

    public static <T> Result<T, HttpError> internalServerError()
    {
        return Result.error(new HttpError(HttpState.INTERNAL_SERVER_ERROR));
    }

    public static <T> Result<T, HttpError> internalServerError(Throwable e)
    {
        var stack = Arrays.asList(e.getStackTrace()).stream().map("at %s"::formatted).collect(Collectors.toCollection(ArrayList::new));
        return internalServerError(Data.asLinkedHashMap()
            .add("error", "Exception")
            .add("message", e.getMessage())
            .add("Caused by: " + e.getClass().getName(), stack.toArray(new String[0])));
    }
    
    public static class HttpErrorSerialization extends StdSerializer<HttpError>
    {
        private static final long serialVersionUID = 2164373842514268068L;

        public HttpErrorSerialization()
        {
            super(HttpError.class);
        }

        @Override
        public void serialize(HttpError value, JsonGenerator gen, SerializerProvider provider) throws IOException
        {
            gen.writeStartObject();
            gen.writeNumberField("code", value.state().getCode());
            gen.writeStringField("state", value.state().getState());
            gen.writePOJOField("data", value.state().getData());
            gen.writeEndObject();
        }
    }
}
