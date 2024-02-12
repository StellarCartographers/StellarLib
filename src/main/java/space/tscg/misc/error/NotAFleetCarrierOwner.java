/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.misc.error;

import panda.std.Result;

import space.tscg.collections.Data;
import space.tscg.web.HttpError;
import space.tscg.web.States;

public class NotAFleetCarrierOwner
{
    public <T> Result<T, HttpError> error()
    {
        return new HttpError(States.NO_CONTENT.withData(Data.asLinkedHashMap().add("exception", "NotAFleetCarrierOwner").add("error", "Not a FleetCarrier Owner"))).asResult();
    }

    @SuppressWarnings("rawtypes")
    public static boolean is(Object obj)
    {
        if (obj == null)
            return false;
        if (obj instanceof Data)
        {
            Data data = (Data) obj;
            if (data.isMap())
            {
                return data.getAsMap().get("exception").equals("NotAFleetCarrierOwner");
            } else
            {
                return false;
            }
        }
        return obj instanceof NotAFleetCarrierOwner;
    }
}
