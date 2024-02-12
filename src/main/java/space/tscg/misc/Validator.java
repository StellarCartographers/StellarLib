/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.misc;

import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.LongValidator;

public interface Validator
{
    static int Integer(String value)
    {
        return IntegerValidator.getInstance().validate(value);
    }

    static long Long(String value)
    {
        return LongValidator.getInstance().validate(value);
    }
}
