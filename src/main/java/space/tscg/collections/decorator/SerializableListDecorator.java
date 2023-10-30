package space.tscg.collections.decorator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;

public abstract class SerializableListDecorator<E> extends ListDecorator<E>
{
    private static final long serialVersionUID = -8640876490554233721L;

    protected SerializableListDecorator(final List<E> list)
    {
        super(list);
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
