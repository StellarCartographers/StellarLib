/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.decorator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ListDecorator<E> extends CollectionDecorator<E> implements List<E>
{
    private static final long serialVersionUID = -568539845885101237L;

    protected ListDecorator(final List<E> list)
    {
        super(list);
    }

    @Override
    protected List<E> decorated()
    {
        return (List<E>) super.decorated();
    }

    @Override
    public boolean equals(final Object object)
    {
        return object == this || decorated().equals(object);
    }

    @Override
    public int hashCode()
    {
        return decorated().hashCode();
    }

    @Override
    public void add(final int index, final E object)
    {
        decorated().add(index, object);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> coll)
    {
        return decorated().addAll(index, coll);
    }

    @Override
    public E get(final int index)
    {
        return decorated().get(index);
    }

    @Override
    public int indexOf(final Object object)
    {
        return decorated().indexOf(object);
    }

    @Override
    public int lastIndexOf(final Object object)
    {
        return decorated().lastIndexOf(object);
    }

    @Override
    public ListIterator<E> listIterator()
    {
        return decorated().listIterator();
    }

    @Override
    public ListIterator<E> listIterator(final int index)
    {
        return decorated().listIterator(index);
    }

    @Override
    public E remove(final int index)
    {
        return decorated().remove(index);
    }

    @Override
    public E set(final int index, final E object)
    {
        return decorated().set(index, object);
    }

    @Override
    public List<E> subList(final int fromIndex, final int toIndex)
    {
        return decorated().subList(fromIndex, toIndex);
    }
}
