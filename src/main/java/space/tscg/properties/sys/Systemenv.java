package space.tscg.properties.sys;

import java.util.Map;

import space.tscg.properties.Variable;

public interface Systemenv
{
    static Systemenv load(Object... defaultVariables)
    {
        return new SystemenvBuilder().load(defaultVariables);
    }
    
    static Systemenv load()
    {
        return new SystemenvBuilder().load();
    }
    
    Map<String, Variable> variables();
    
    /**
     * Retrieves the value of the environment variable specified by key
     * @param key the environment variable
     * @return the value of the environment variable
     */
    Variable get(String key);

    /**
     * Retrieves the value of the environment variable specified by key.
     * If the key does not exist, then the default value is returned
     * @param key the environment variable
     * @param defaultValue the default value to return
     * @return the value of the environment variable or default value
     */
    Variable get(String key, Object defaultValue);
}
