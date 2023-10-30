package space.tscg.collections.decorator;

import java.util.Iterator;
import java.util.Objects;

public abstract class UntypedIteratorDecorator<I, O> implements Iterator<O>
{
    private final Iterator<I> iterator;

    protected UntypedIteratorDecorator(final Iterator<I> iterator)
    {
        this.iterator = Objects.requireNonNull(iterator, "iterator");
    }

    protected Iterator<I> getIterator()
    {
        return iterator;
    }

    @Override
    public boolean hasNext()
    {
        return iterator.hasNext();
    }

    @Override
    public void remove()
    {
        iterator.remove();
    }
}
