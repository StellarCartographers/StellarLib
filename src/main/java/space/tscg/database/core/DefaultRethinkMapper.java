/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.database.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.function.Consumer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DefaultRethinkMapper
{
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private ObjectMapper mapper;
    static
    {
        /* !formatter */
        if (instance0().mapper() == null)
            instance0().mapper(new ObjectMapper()
                            .registerModule(new JavaTimeModule())
                            .setSerializationInclusion(Include.NON_NULL)
                            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
                            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
        /* @formatter */
    }

    static ObjectMapper getDefault()
    {
        return instance0().mapper();
    }

    public static void addConfigurations(Consumer<ObjectMapper> mapper)
    {
        mapper.accept(instance0().mapper());
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
