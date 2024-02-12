/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.decorator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class CollectionDecorator<E> implements Collection<E>, Serializable
{
    private static final long serialVersionUID = 936096791130205616L;
    private Collection<E>     collection;

    protected Collection<E> decorated()
    {
        return collection;
    }

    protected void setCollection(final Collection<E> coll)
    {
        this.collection = coll;
    }

    @Override
    public boolean add(final E object)
    {
        return decorated().add(object);
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll)
    {
        return decorated().addAll(coll);
    }

    @Override
    public void clear()
    {
        decorated().clear();
    }

    @Override
    public boolean contains(final Object object)
    {
        return decorated().contains(object);
    }

    @Override
    public boolean isEmpty()
    {
        return decorated().isEmpty();
    }

    @Override
    public Iterator<E> iterator()
    {
        return decorated().iterator();
    }

    @Override
    public boolean remove(final Object object)
    {
        return decorated().remove(object);
    }

    @Override
    public int size()
    {
        return decorated().size();
    }

    @Override
    public Object[] toArray()
    {
        return decorated().toArray();
    }

    @Override
    public <T> T[] toArray(final T[] object)
    {
        return decorated().toArray(object);
    }

    @Override
    public boolean containsAll(final Collection<?> coll)
    {
        return decorated().containsAll(coll);
    }

    @Override
    public boolean removeIf(final Predicate<? super E> filter)
    {
        return decorated().removeIf(filter);
    }

    @Override
    public boolean removeAll(final Collection<?> coll)
    {
        return decorated().removeAll(coll);
    }

    @Override
    public boolean retainAll(final Collection<?> coll)
    {
        return decorated().retainAll(coll);
    }

    @Override
    public String toString()
    {
        return decorated().toString();
    }
}
