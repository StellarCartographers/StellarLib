/**
 * Copyright (c) 2023  The Stellar Cartographers' Guild.
 *
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
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
        return new HttpError(States.NO_CONTENT
            .withData(Data.asLinkedHashMap()
            .add("exception", "NotAFleetCarrierOwner")
            .add("error", "Not a FleetCarrier Owner")))
            .asResult();
    }
    
    @SuppressWarnings("rawtypes")
    public static boolean is(Object obj)
    {
        if(obj == null)
            return false;
        
        if(obj instanceof Data) {
            Data data = (Data) obj;
            if(data.isMap()) {
                return data.getAsMap().get("exception").equals("NotAFleetCarrierOwner");
            } else {
                return false;
            }
        }

        return obj instanceof NotAFleetCarrierOwner;
    }
}