/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.iterator;

import java.util.ListIterator;
import java.util.Objects;

import space.tscg.collections.Unalterable;

public final class UnalterableListIterator<E> implements ListIterator<E>, Unalterable
{
    private final ListIterator<? extends E> iterator;

    public static <E> ListIterator<E> unalterableListIterator(final ListIterator<? extends E> iterator)
    {
        Objects.requireNonNull(iterator, "iterator");
        if (iterator instanceof Unalterable)
        {
            @SuppressWarnings("unchecked")
            final ListIterator<E> tmpIterator = (ListIterator<E>) iterator;
            return tmpIterator;
        }
        return new UnalterableListIterator<>(iterator);
    }

    private UnalterableListIterator(final ListIterator<? extends E> iterator)
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
    public int nextIndex()
    {
        return iterator.nextIndex();
    }

    @Override
    public boolean hasPrevious()
    {
        return iterator.hasPrevious();
    }

    @Override
    public E previous()
    {
        return iterator.previous();
    }

    @Override
    public int previousIndex()
    {
        return iterator.previousIndex();
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("remove() is not supported");
    }

    @Override
    public void set(final E obj)
    {
        throw new UnsupportedOperationException("set() is not supported");
    }

    @Override
    public void add(final E obj)
    {
        throw new UnsupportedOperationException("add() is not supported");
    }
}
