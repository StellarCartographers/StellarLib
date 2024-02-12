/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.decorator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Set;

public abstract class SerializableSetDecorator<E> extends SetDecorator<E>
{
    private static final long serialVersionUID = -7994806843077078896L;

    protected SerializableSetDecorator(final Set<E> set)
    {
        super(set);
    }

    private void writeObject(final ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
        out.writeObject(decorated());
    }

    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        setCollection((Collection<E>) in.readObject());
    }
}
