/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.member;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.*;

import space.tscg.api.Name;
import space.tscg.member.CarrierManager.PublicNames;

@Data
@Builder(builderMethodName = "Builder")
@Jacksonized
public class PublicChannel
{
    public final String        id;
    public Map<String, String> messages;

    public void setMessageId(Name name, String id)
    {
        this.messages.put(name.toString(), id);
    }

    String getMessageId(Name name)
    {
        return this.messages.get(name.toString()).replace("::hidden", "");
    }

    public static PublicChannel create(String id)
    {
        return new PublicChannel(id, populateMessages());
    }

    public static HashMap<String, String> populateMessages()
    {
        var map = new HashMap<String, String>();
        for (var name : PublicNames.values())
            map.put(name.toString(), "unset");
        return map;
    }

    public void setHidden(Name key)
    {
        String currentVal = (String) messages.get(key.toString());
        if (!currentVal.endsWith("::hidden"))
        {
            currentVal = currentVal + "::hidden";
            messages.put(key.toString(), currentVal);
        }
    }

    public void setVisable(Name key)
    {
        String currentVal = messages.get(key.toString());
        if (currentVal.endsWith("::hidden"))
        {
            currentVal = currentVal.replace("::hidden", "");
            messages.put(key.toString(), currentVal);
        }
    }

    public boolean isHidden(Name key)
    {
        return messages.get(key.toString()).endsWith("::hidden");
    }
}
