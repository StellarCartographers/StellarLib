/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.misc;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil
{
    public static String capitalize(String s)
    {
        if (s.length() == 0)
        {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static String uncapitalize(String s)
    {
        if (s.length() == 0)
        {
            return s;
        }
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }
}
