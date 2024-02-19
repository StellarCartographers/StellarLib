/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.*;

import elite.dangerous.capi.modal.fc.orders.Orders;

import space.tscg.api.carrier.*;
import space.tscg.collections.DiffMap;

@Data
@Builder(builderMethodName = "Builder", toBuilder = true)
@Jacksonized
public class FleetCarrier implements IFleetCarrier
{
    private String           id;
    private String           callsign;
    private String           name;
    private String           system;
    private int              fuel;
    private long             lastUpdated;
    private ICarrierServices services;
    private Orders           orders;
    private ICarrierMarket   market;

    @JsonIgnore
    public Duration getDurationSinceLastUpdate()
    {
        return Duration.between(Instant.ofEpochSecond(lastUpdated), Instant.now());
    }

    /**
     * {@inheritDoc}
     */
    /* !formatter */
    @Override
    public DiffMap diff(IFleetCarrier other)
    {
        return DiffMap.Builder()
            .append("name", this.name, other.name())
            .append("fuel", this.fuel, other.fuel())
            .append("system", this.system, other.system())
            .appendDiff("services", this.services.diff(other.services()))
            .appendDiff("orders", DiffMap.fromObjects(this.orders, other.orders()))
            .build();
    }
    /* @formatter */
    
    @Override
    public String getId()
    {
        return id;
    }
}
