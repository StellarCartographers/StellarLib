package space.tscg.collections.decorator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Set;

public abstract class SerializableSetDecorator<E> extends SetDecorator<E>
{
    private static final long serialVersionUID = -7994806843077078896L;

    protected SerializableSetDecorator(final Set<E> set)
    {
        super(set);
    }

    private void writeObject(final ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
        out.writeObject(decorated());
    }

    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        setCollection((Collection<E>) in.readObject());
    }
}
