/*
 * This file is part of Elite4J, licensed under MIT.
 * 
 * Copyright (c) 2024 StellarCartographers.
 * 
 * You should have received a copy of the MIT license along with this program.
 * If not, see <https://opensource.org/licenses/MIT>.
 */
package space.tscg.misc;

import lombok.AllArgsConstructor;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

import elite.dangerous.model.inerf.ID;

import space.tscg.misc.FDevID.*;

@AllArgsConstructor
@SuppressWarnings("serial")
@JsonDeserialize(using = DeserializeAdapter.class)
@JsonSerialize(using = SerializeAdapter.class)
public final class FDevID implements ID
{
    private final Object id;

    public static FDevID of(int id)
    {
        return new FDevID(id);
    }

    @Override
    public String asText()
    {
        return id.toString();
    }

    @Override
    public Long toLong()
    {
        return Validator.Long(id.toString());
    }

    static class SerializeAdapter extends StdSerializer<FDevID>
    {
        public SerializeAdapter()
        {
            super(FDevID.class);
        }

        @Override
        public void serialize(FDevID value, JsonGenerator gen, SerializerProvider provider) throws IOException
        {
            gen.writePOJO(value.toLong());
        }
    }

    static class DeserializeAdapter extends StdDeserializer<FDevID>
    {
        protected DeserializeAdapter()
        {
            super(FDevID.class);
        }

        @Override
        public FDevID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException
        {
            JsonNode node = p.getCodec().readTree(p);
            return new FDevID(node.asText());
        }
    }
}
