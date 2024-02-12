/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.decorator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class SetDecorator<E> extends CollectionDecorator<E> implements Set<E>
{
    private static final long serialVersionUID = -3096807767457886899L;

    protected SetDecorator(final Set<E> set)
    {
        super(set);
    }

    @Override
    protected Set<E> decorated()
    {
        return (Set<E>) super.decorated();
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
}
