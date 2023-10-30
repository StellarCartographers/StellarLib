package space.tscg.collections.decorator;

import java.util.Iterator;

public abstract class IteratorDecorator<E> extends UntypedIteratorDecorator<E, E>
{
    protected IteratorDecorator(final Iterator<E> iterator)
    {
        super(iterator);
    }

    @Override
    public E next()
    {
        return getIterator().next();
    }
}
