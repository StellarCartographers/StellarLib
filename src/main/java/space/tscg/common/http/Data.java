package space.tscg.common.http;

import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Data
{
    @SuppressWarnings("rawtypes")
    private Map dataMap;
    
    public static Data map()
    {
        return new Data(new HashMap<>());
    }
        
    @SuppressWarnings("rawtypes")
    public <T> Data with(Map map)
    {
        this.dataMap = map;
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T> Data with(String key, T data)
    {
        this.dataMap.put(key, data);
        return this;
    }
    
    @SuppressWarnings("rawtypes")
    Map getDataMap()
    {
        return dataMap;
    }
}
