package space.tscg.misc.json;

import java.util.function.Function;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.databind.node.NullNode;

import panda.std.Blank;

public final class Null extends NullNode
{
    private static final long serialVersionUID = 5896385324954802512L;

    private volatile static Null instance;

    public static final @NotNull Function<Blank, String> string()
    {
        return new Function<Blank, String>()
        {
            
            @Override
            public String apply(Blank t)
            {
                return get().asText();
            }
        };
    }
    
    public static final boolean isEqual(Object obj)
    {
        return get().equals(obj);
    }
    
    public static Null get()
    {
        if (instance == null) {
            synchronized (Null.class) {
                if (instance == null) {
                    instance = new Null();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == null) { return true; }
        
        if(o instanceof String && ((String) o).equalsIgnoreCase("null")) { return true; }
        
        return super.equals(o);
    }
}
