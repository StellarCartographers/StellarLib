package space.tscg.collections.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

import org.jetbrains.annotations.Unmodifiable;

import space.tscg.collections.Unalterable;
import space.tscg.collections.decorator.SerializableListDecorator;
import space.tscg.collections.iterator.UnalterableIterator;
import space.tscg.collections.iterator.UnalterableListIterator;

public class UnalterableList<E> extends SerializableListDecorator<E> implements Unalterable
{
    private static final long serialVersionUID = 3135461913828697256L;

    public static <E> List<E> unmodifiableList(final List<? extends E> list)
    {
        if (list instanceof Unmodifiable) {
            @SuppressWarnings("unchecked") // safe to upcast
            final List<E> tmpList = (List<E>) list;
            return tmpList;
        }
        return new UnalterableList<>(list);
    }

    @SuppressWarnings("unchecked") // safe to upcast
    public UnalterableList(final List<? extends E> list)
    {
        super((List<E>) list);
    }

    @Override
    public Iterator<E> iterator()
    {
        return UnalterableIterator.unalterableIterator(decorated().iterator());
    }

    @Override
    public boolean add(final Object object)
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

    @Override
    public ListIterator<E> listIterator()
    {
        return UnalterableListIterator.unalterableListIterator(decorated().listIterator());
    }

    @Override
    public ListIterator<E> listIterator(final int index)
    {
        return UnalterableListIterator.unalterableListIterator(decorated().listIterator(index));
    }

    @Override
    public void add(final int index, final E object)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> coll)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(final int index)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public E set(final int index, final E object)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(final int fromIndex, final int toIndex)
    {
        final List<E> sub = decorated().subList(fromIndex, toIndex);
        return new UnalterableList<>(sub);
    }
}
