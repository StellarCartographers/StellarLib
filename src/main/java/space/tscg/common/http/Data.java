package space.tscg.common.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.tscg.common.http.Data.DataSerialization;
import space.tscg.common.json.Json;
import space.tscg.common.json.JsonWrapper;

@JsonSerialize(using = DataSerialization.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Data<T> implements JsonWrapper
{
    @Setter
    protected T data;

    private Data(Supplier<T> supplier)
    {
        this.data = supplier.get();
    }

    /**
     * Returns the instance of our Data type. The returned types mutability is based
     * on the explict type that was passed to this instance
     *
     * @return T data
     */
    public T getData()
    {
        return data;
    }

    @Override
    public String json()
    {
        return Json.string(getData());
    }

    @Override
    public String prettyPrintJson()
    {
        return Json.prettyString(getData());
    }
    
    public static <T> Data<T> of(T data)
    {
        return new Data<>(data);
    }
    
    //////////////////////////////////////////////
    // -- List Implementation Static Methods -- //
    //////////////////////////////////////////////

    /**
     * Creates a new DataList instance backed by a {@code ArrayList<V>}
     *
     * @param <V> the element Type 
     * @return DataList&#60;V&#62;
     * @implNote Using ArrayList will result in the elements ordered Alphabetically.
     *           Use {@link Data#asLinkedList() asLinkedList()} if you want
     *           to retain the order of elements as they are added.
     */
    public static <V> DataList<V> asArrayList()
    {
        return asList(ArrayList::new);
    }

    /**
     * Creates a new DataList instance backed by a {@code LinkedList<V>}
     *
     * @param <V> the element Type 
     * @return DataList&#60;V&#62;
     * @implNote 
     *          Using LinkedList keeps the order in which elements were inserted into the list 
     *          (insertion-order). You can safely use {@link Data#asArrayList() asArrayList()} 
     *          if insert order is not important.
     */
    public static <V> DataList<V> asLinkedList()
    {
        return asList(LinkedList::new);
    }

    /**
     * Creates a new DataList instance backed by the provided {@code List<V> } 
     * Supplier
     *
     * @param <V> the Values Type 
     * @param supplier the backing List supplier ( eg: {@code ArrayList::new} )
     * @return DataList&#60;V&#62;
     */
    public static <V> DataList<V> asList(Supplier<List<V>> supplier)
    {
        return new DataList<>(supplier);
    }
    /////////////////////////////////////////////
    // -- Map Implementation Static Methods -- //
    /////////////////////////////////////////////

    /**
     * Creates a new DataMap instance backed by a {@code HashMap<String, V>}
     *
     * @param <V> the Values Type 
     * @return DataMap&#60;V&#62;
     * @implNote Using HashMap will result in the key fields ordered Alphabetically.
     *           Use {@link Data#asLinkedHashMap() asLinkedHashMap()} if you want
     *           to retain the order of keys as they are added.
     */
    public static <V> DataMap<V> asHashMap()
    {
        return asMap(HashMap::new);
    }

    /**
     * Creates a new DataMap instance backed by a {@code LinkedHashMap<String, V>}
     *
     * @param <V> the Values Type 
     * @return DataMap&#60;V&#62;
     * @implNote 
     *      Using LinkedHashMap keeps the order in which keys were inserted into the map 
     *      (insertion-order). You can safely use {@link Data#asHashMap() asHashMap()} if 
     *      key order is not important.
     */
    public static <V> DataMap<V> asLinkedHashMap()
    {
        return asMap(LinkedHashMap::new);
    }

    /**
     * Creates a new DataMap instance backed by the provided {@code Map<String,V> } 
     * Supplier
     *
     * @param <V> the Values Type 
     * @param supplier the backing Map supplier ( eg: {@code HashMap::new} )
     * @return DataMap&#60;V&#62;
     */
    public static <V> DataMap<V> asMap(Supplier<Map<String, V>> supplier)
    {
        return new DataMap<>(supplier);
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class DataList<T> extends Data<List<T>>
    {
        DataList(List<T> map)
        {
            super(map);
        }

        DataList(Supplier<List<T>> supplier)
        {
            super(supplier);
        }

        /**
         * Sets the backing list with the elements of the supplied list
         * then returns this instance<br>
         * <ul><li><strong>NOTE: Replaces list entirely</strong></li></ul>
         *
         * @param list List&#60;T&#62;
         * @return returns this DataList&#60;T&#62;, useful for chaining
         */
        public DataList<T> set(List<T> list)
        {
            this.data = list;
            return this;
        }

        /**
         * Adds all elements of the supplied list then returns this instance<br>
         *
         * @param list List&#60;T&#62;
         * @return returns this DataList&#60;T&#62;, useful for chaining
         */
        public DataList<T> addAll(List<T> list)
        {
            this.data.addAll(list);
            return this;
        }

        /**
         * Add element to the backing list then returns this instance<br>.
         *
         * @param element 
         * @return returns this DataList&#60;T&#62;, useful for chaining
         */
        public DataList<T> add(T element)
        {
            this.data.add(element);
            return this;
        }
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class DataMap<V> extends Data<Map<String, V>>
    {
        DataMap(Map<String, V> map)
        {
            super(map);
        }

        DataMap(Supplier<Map<String, V>> supplier)
        {
            super(supplier);
        }

        /**
         * Sets the backing map with the Key/Value pairs of the supplied map
         * then returns this instance<br>
         * <ul><li><strong>NOTE: Replaces map entirely</strong></li></ul>
         *
         * @param map Map&#60;String, T&#62;
         * @return returns this DataMap&#60;String, T&#62;, useful for chaining
         */
        public DataMap<V> set(Map<String, V> map)
        {
            this.data = map;
            return this;
        }

        /**
         * Adds all Key/Value pairs of the supplied map then returns this instance<br>
         *
         * @param map Map&#60;String, T&#62;
         * @return returns this DataMap&#60;String, T&#62;, useful for chaining
         */
        public DataMap<V> addAll(Map<String, V> map)
        {
            this.data.putAll(map);
            return this;
        }

        /**
         * Adds a single Key/Value pair to the backing map then returns this instance<br>
         *
         * @param key 
         * @param data
         * @return returns this DataMap&#60;String, T&#62;, useful for chaining
         */
        public DataMap<V> add(String key, V data)
        {
            this.data.put(key, data);
            return this;
        }
    }

    public static class DataSerialization extends StdSerializer<Data<?>>
    {
        private static final long serialVersionUID = -6670569536298587549L;

        public DataSerialization()
        {
            super(Data.class, false);
        }

        @Override
        public void serialize(Data<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException
        {
            gen.writePOJO(value.getData());
        }
    }
}
