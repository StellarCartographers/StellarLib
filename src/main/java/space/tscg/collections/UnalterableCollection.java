package space.tscg.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

import space.tscg.collections.decorator.CollectionDecorator;
import space.tscg.collections.iterator.UnalterableIterator;

public final class UnalterableCollection<E> extends CollectionDecorator<E> implements Unalterable
{
    private static final long serialVersionUID = -4289806624361321213L;

    /**
     * Factory method to create an unmodifiable collection.
     * <p>
     * If the collection passed in is already unmodifiable, it is returned.
     *
     * @param <T>
     *            the type of the elements in the collection
     * @param coll
     *            the collection to decorate, must not be null
     * 
     * @return an unmodifiable collection
     * 
     * @throws NullPointerException
     *             if collection is null
     * 
     * @since 4.0
     */
    public static <T> Collection<T> unalterableCollection(final Collection<? extends T> coll)
    {
        if (coll instanceof Unalterable) {
            @SuppressWarnings("unchecked") // safe to upcast
            final Collection<T> tmpColl = (Collection<T>) coll;
            return tmpColl;
        }
        return new UnalterableCollection<>(coll);
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param coll
     *            the collection to decorate, must not be null
     * 
     * @throws NullPointerException
     *             if collection is null
     */
    @SuppressWarnings("unchecked") // safe to upcast
    private UnalterableCollection(final Collection<? extends E> coll)
    {
        super((Collection<E>) coll);
    }

    @Override
    public Iterator<E> iterator()
    {
        return UnalterableIterator.unalterableIterator(decorated().iterator());
    }

    @Override
    public boolean add(final E object)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(final Object object)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeIf(final Predicate<? super E> filter)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(final Collection<?> coll)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(final Collection<?> coll)
    {
        throw new UnsupportedOperationException();
    }
}
