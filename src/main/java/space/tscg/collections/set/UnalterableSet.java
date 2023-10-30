package space.tscg.collections.set;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

import space.tscg.collections.Unalterable;
import space.tscg.collections.decorator.SerializableSetDecorator;
import space.tscg.collections.iterator.UnalterableIterator;

public final class UnalterableSet<E> extends SerializableSetDecorator<E> implements Unalterable
{
    private static final long serialVersionUID = 6499119872185240161L;

    public static <E> Set<E> unalterableSet(final Set<? extends E> set)
    {
        if (set instanceof Unalterable) {
            @SuppressWarnings("unchecked")
            final Set<E> tmpSet = (Set<E>) set;
            return tmpSet;
        }
        return new UnalterableSet<>(set);
    }

    @SuppressWarnings("unchecked")
    private UnalterableSet(final Set<? extends E> set)
    {
        super((Set<E>) set);
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
