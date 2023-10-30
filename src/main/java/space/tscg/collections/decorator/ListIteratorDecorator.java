package space.tscg.collections.decorator;

import java.util.ListIterator;
import java.util.Objects;

public class ListIteratorDecorator<E> implements ListIterator<E>
{
    private final ListIterator<E> iterator;

    public ListIteratorDecorator(final ListIterator<E> iterator)
    {
        this.iterator = Objects.requireNonNull(iterator, "iterator");
    }

    protected ListIterator<E> getListIterator()
    {
        return iterator;
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasNext()
    {
        return iterator.hasNext();
    }

    /** {@inheritDoc} */
    @Override
    public E next()
    {
        return iterator.next();
    }

    /** {@inheritDoc} */
    @Override
    public int nextIndex()
    {
        return iterator.nextIndex();
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPrevious()
    {
        return iterator.hasPrevious();
    }

    /** {@inheritDoc} */
    @Override
    public E previous()
    {
        return iterator.previous();
    }

    /** {@inheritDoc} */
    @Override
    public int previousIndex()
    {
        return iterator.previousIndex();
    }

    /** {@inheritDoc} */
    @Override
    public void remove()
    {
        iterator.remove();
    }

    /** {@inheritDoc} */
    @Override
    public void set(final E obj)
    {
        iterator.set(obj);
    }

    /** {@inheritDoc} */
    @Override
    public void add(final E obj)
    {
        iterator.add(obj);
    }
}
