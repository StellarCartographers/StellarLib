/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.web;

import panda.std.Result;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import space.tscg.collections.Data;
import space.tscg.web.HttpError.HttpErrorSerialization;

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
        return Result.error(new HttpError(States.UNAUTHORIZED.withData(data)));
    }

    public static <T> Result<T, HttpError> forbidden(Data data)
    {
        return Result.error(new HttpError(States.FORBIDDEN.withData(data)));
    }

    public static <T> Result<T, HttpError> badRequest(Data data)
    {
        return Result.error(new HttpError(States.BAD_REQUEST.withData(data)));
    }

    public static <T> Result<T, HttpError> notFound(Data data)
    {
        return Result.error(new HttpError(States.NOT_FOUND.withData(data)));
    }

    public static <T> Result<T, HttpError> conflict(Data data)
    {
        return Result.error(new HttpError(States.CONFLICT.withData(data)));
    }

    public static <T> Result<T, HttpError> internalServerError(Data data)
    {
        return Result.error(new HttpError(States.INTERNAL_SERVER_ERROR.withData(data)));
    }

    public static <T> Result<T, HttpError> unauthorized()
    {
        return Result.error(new HttpError(States.UNAUTHORIZED));
    }

    public static <T> Result<T, HttpError> forbidden()
    {
        return Result.error(new HttpError(States.FORBIDDEN));
    }

    public static <T> Result<T, HttpError> badRequest()
    {
        return Result.error(new HttpError(States.BAD_REQUEST));
    }

    public static <T> Result<T, HttpError> notFound()
    {
        return Result.error(new HttpError(States.NOT_FOUND));
    }

    public static <T> Result<T, HttpError> conflict()
    {
        return Result.error(new HttpError(States.CONFLICT));
    }

    public static <T> Result<T, HttpError> internalServerError()
    {
        return Result.error(new HttpError(States.INTERNAL_SERVER_ERROR));
    }

    public static <T> Result<T, HttpError> internalServerError(Throwable e)
    {
        var stack = Arrays.asList(e.getStackTrace()).stream().map("at %s"::formatted).collect(Collectors.toCollection(ArrayList::new));
        return internalServerError(Data.asLinkedHashMap().add("error", "Exception").add("message", e.getMessage()).add("Caused by: " + e.getClass().getName(), stack.toArray(new String[0])));
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
            gen.writeNumberField("code", value.state().code());
            gen.writeStringField("state", value.state().state());
            gen.writePOJOField("data", value.state().data());
            gen.writeEndObject();
        }
    }
}
