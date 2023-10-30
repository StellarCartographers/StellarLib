package space.tscg.properties.sys;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import space.tscg.properties.Variable;

public class SystemenvBuilder
{
    public Systemenv load(Object... defaultVariables)
    {
        return new SystemenvImpl(defaultVariables);
    }
    
    public Systemenv load()
    {
        return new SystemenvImpl();
    }

    static class SystemenvImpl implements Systemenv
    {
        private final Map<String, Variable> variables = new HashMap<String, Variable>();

        public SystemenvImpl()
        {
            System.getenv().entrySet().stream().forEach(s -> variables.put(s.getKey().toUpperCase(), new Variable(s.getValue())));
        }
        
        public SystemenvImpl(Object... defaultVariables)
        {
            Objects.requireNonNull(defaultVariables);
            var variList = List.of(Arrays.asList(defaultVariables).stream().map(Object::toString).toArray(String[]::new));
            System.getenv().entrySet().stream().filter(e -> variList.contains(e.getKey())).forEach(e -> variables.put(e.getKey().toUpperCase(), new Variable(e.getValue())));
        }

        @Override
        public Variable get(String key)
        {
            return variables.get(key.toUpperCase());
        }

        @Override
        public Variable get(String key, Object defaultValue)
        {
            return variables.containsKey(key.toUpperCase()) ? variables.get(key.toUpperCase()) : new Variable(defaultValue);
        }

        @Override
        public Map<String, Variable> variables()
        {
            return variables;
        }
    }
}
