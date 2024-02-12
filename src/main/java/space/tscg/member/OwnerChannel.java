/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.member;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

import java.util.*;

import space.tscg.api.Name;
import space.tscg.member.CarrierManager.OwnerNames;

@Getter
@Builder(builderMethodName = "Builder")
@Jacksonized
public class OwnerChannel
{
    private final String        id;
    private Map<String, String> messages;

    public void setMessageId(Name name, String id)
    {
        this.messages.put(name.toString(), id);
    }

    String getMessageId(Name name)
    {
        return this.messages.get(name.toString());
    }

    public static OwnerChannel create(String id)
    {
        return new OwnerChannel(id, populateMessages());
    }

    public static HashMap<String, String> populateMessages()
    {
        var map = new HashMap<String, String>();
        for (var name : OwnerNames.values())
            map.put(name.toString(), "unset");
        return map;
    }

    public boolean isCorrectChannel(MessageChannelUnion channel)
    {
        return channel.getId().equals(id);
    }
}
