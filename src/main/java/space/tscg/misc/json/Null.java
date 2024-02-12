/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.misc.json;

import panda.std.Blank;

import com.fasterxml.jackson.databind.node.NullNode;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public final class Null extends NullNode
{
    private static final long    serialVersionUID = 5896385324954802512L;
    private volatile static Null instance;

    public static final @NotNull Function<Blank, String> string()
    {
        return new Function<Blank, String>()
        {
            @Override
            public String apply(Blank t)
            {
                return get().asText();
            }
        };
    }

    public static final boolean isEqual(Object obj)
    {
        return get().equals(obj);
    }

    public static Null get()
    {
        if (instance == null)
        {
            synchronized (Null.class)
            {
                if (instance == null)
                {
                    instance = new Null();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
        {
            return true;
        }
        if (o instanceof String && ((String) o).equalsIgnoreCase("null"))
        {
            return true;
        }
        return super.equals(o);
    }
}
