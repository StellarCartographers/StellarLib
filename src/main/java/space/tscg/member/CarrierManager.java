/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.member;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import com.fasterxml.jackson.annotation.JsonIgnore;

import space.tscg.api.Name;

import static space.tscg.misc.StaticUtil.*;

@Getter
@Builder(builderMethodName = "Builder")
@Jacksonized
public class CarrierManager
{
    @Setter
    @Builder.Default
    private String        chatThreadId = _EMPTY;
    @Builder.Default
    private OwnerChannel  ownerChannel = OwnerChannel.Builder().build();
    @Builder.Default
    private PublicChannel publicChannel = PublicChannel.Builder().build();

    @JsonIgnore
    public static CarrierManager create(String ownerId, String publicId)
    {
        var ownerChnl = OwnerChannel.create(ownerId);
        var publicChnl = PublicChannel.create(publicId);
        return new CarrierManager(null, ownerChnl, publicChnl);
    }
    
    public String getMessageId(Name name)
    {
        if(name.equals(PublicNames.INFO))
            return chatThreadId;
        else if(name instanceof PublicNames)
            return publicChannel.getMessageId(name);
        else if(name instanceof OwnerNames)
            return ownerChannel.getMessageId(name);
        else
            throw new IllegalArgumentException("Name: %s is not a supported Enum".formatted(name.toString()));
    } 
    
    public enum PublicNames implements Name
    {
        HEADER,
        LOCATION,
        SERVICES,
        ORDERS,
        SHIPYARD,
        OUTFITTING,
        INFO;

        @Override
        public String toString()
        {
            return super.toString().toLowerCase();
        }
    }
    
    public enum OwnerNames implements Name
    {
        INFO,
        REFRESH,
        OPTIONS;

        @Override
        public String toString()
        {
            return super.toString().toLowerCase();
        }
    }
}
