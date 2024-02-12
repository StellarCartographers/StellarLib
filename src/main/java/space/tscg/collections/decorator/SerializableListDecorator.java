/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.collections.decorator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class SerializableListDecorator<E> extends ListDecorator<E>
{
    private static final long serialVersionUID = -8640876490554233721L;

    protected SerializableListDecorator(final List<E> list)
    {
        super(list);
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
