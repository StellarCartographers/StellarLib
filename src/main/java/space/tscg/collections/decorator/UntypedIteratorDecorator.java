/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
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
