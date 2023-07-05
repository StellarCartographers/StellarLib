package space.tscg.common;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class Blank
{

    public static final Blank NADA = new Blank();

    public Void toVoid()
    {
        return voidness();
    }

    @Override
    public int hashCode()
    {
        return 0;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Blank;
    }

    public static Void voidness()
    {
        return null;
    }
}
