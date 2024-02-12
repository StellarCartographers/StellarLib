/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.web.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DefaultDomain
{
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private String domain;
    static
    {
        if (instance0().domain() == null)
            instance0().domain("tscg.network");
    }

    static Domain getDefault()
    {
        return Domain.of(instance0().domain());
    }

    public static void setDefault(String domain)
    {
        instance0().domain(domain);
    }

    private volatile static DefaultDomain instance;

    private static DefaultDomain instance0()
    {
        if (instance == null)
        {
            synchronized (DefaultDomain.class)
            {
                if (instance == null)
                {
                    instance = new DefaultDomain();
                }
            }
        }
        return instance;
    }
}
