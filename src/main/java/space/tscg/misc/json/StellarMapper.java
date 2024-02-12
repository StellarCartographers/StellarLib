/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.misc.json;

import panda.std.Blank;
import panda.std.Result;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.jetbrains.annotations.NotNull;
import org.tinylog.Logger;

import java.util.Optional;
import java.util.function.Supplier;

import space.tscg.api.JsonWrapper;

public class StellarMapper
{
    private ObjectMapper                  objMapper;
    private volatile static StellarMapper instance;

    public static StellarMapper get()
    {
        if (instance == null)
        {
            synchronized (StellarMapper.class)
            {
                if (instance == null)
                {
                    instance = new StellarMapper();
                }
            }
        }
        return instance;
    }

    private StellarMapper()
    {
        this.objMapper = new ObjectMapper()
                        .setDefaultPrettyPrinter(new DefaultPrettyPrinter().withArrayIndenter(new DefaultIndenter("    ", DefaultIndenter.SYS_LF)))
                        .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
                        .enable(SerializationFeature.INDENT_OUTPUT)
                        .disable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
                        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public @NotNull <JSON extends JsonWrapper, T> Optional<T> asOptional(JSON value, Class<T> type)
    {
        try
        {
            return Optional.of(this.objMapper.readValue(value.toJson(), type));
        } catch (JsonProcessingException e)
        {
            Logger.error(e);
            return Optional.empty();
        }
    }

    public @NotNull <T> Optional<T> asOptional(Object value, TypeReference<T> type)
    {
        try
        {
            if (value instanceof String)
                return Optional.of(this.objMapper.readValue((String) value, type));
            else
                return Optional.of(this.objMapper.readValue(asOptionalToString(value).get(), type));
        } catch (JsonProcessingException e)
        {
            Logger.error(e);
            return Optional.empty();
        }
    }

    public @NotNull <T> Optional<T> asOptional(Object value, Class<T> type)
    {
        try
        {
            if (value instanceof String)
                return Optional.of(this.objMapper.readValue((String) value, type));
            else
                return Optional.of(this.objMapper.readValue(asOptionalToString(value).get(), type));
        } catch (JsonProcessingException e)
        {
            Logger.error(e);
            return Optional.empty();
        }
    }

    public @NotNull <T> Result<T, Blank> asResult(String value, Class<T> type)
    {
        try
        {
            return Result.ok(this.objMapper.readValue(value, type));
        } catch (JsonProcessingException e)
        {
            Logger.error(e);
            return Result.error();
        }
    }

    public @NotNull <T, K> Result<T, K> asResultOrElse(String value, Class<T> type, Supplier<K> orElse)
    {
        try
        {
            return Result.ok(this.objMapper.readValue(value, type));
        } catch (JsonProcessingException e)
        {
            Logger.error(e);
            return Result.error(orElse.get());
        }
    }

    public @NotNull Optional<String> asOptionalToString(Object value)
    {
        try
        {
            return Optional.of(this.objMapper.writeValueAsString(value));
        } catch (JsonProcessingException e)
        {
            Logger.error(e);
            return Optional.empty();
        }
    }

    public @NotNull Result<String, Blank> asResultToString(Object value)
    {
        try
        {
            return Result.ok(this.objMapper.writeValueAsString(value));
        } catch (JsonProcessingException e)
        {
            Logger.error(e);
            return Result.error();
        }
    }
}
