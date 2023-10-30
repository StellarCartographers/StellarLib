package space.tscg.collections.iterator;

import java.util.Iterator;
import java.util.Objects;

import space.tscg.collections.Unalterable;

public class UnalterableIterator<E> implements Iterator<E>, Unalterable
{
    private final Iterator<? extends E> iterator;

    public static <E> Iterator<E> unalterableIterator(final Iterator<? extends E> iterator)
    {
        Objects.requireNonNull(iterator, "iterator");
        if (iterator instanceof Unalterable) {
            @SuppressWarnings("unchecked")
            final Iterator<E> tmpIterator = (Iterator<E>) iterator;
            return tmpIterator;
        }
        return new UnalterableIterator<>(iterator);
    }

    private UnalterableIterator(final Iterator<? extends E> iterator)
    {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext()
    {
        return iterator.hasNext();
    }

    @Override
    public E next()
    {
        return iterator.next();
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}
