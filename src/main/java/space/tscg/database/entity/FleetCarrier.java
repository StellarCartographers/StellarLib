/**
 * Copyright (c) 2023  The Stellar Cartographers' Guild.
 *
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */
package space.tscg.database.entity;

import elite.dangerous.capi.modal.fleetcarrier.Orders;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import space.tscg.api.carrier.IFleetCarrier;
import space.tscg.collections.DiffMap;
import space.tscg.database.DefinedTable;
import space.tscg.misc.DiffMapper;

@Data
@Builder(builderMethodName = "Builder", toBuilder = true)
@Jacksonized
public class FleetCarrier implements IFleetCarrier {
    
    private String id;

    private String callsign;

    private String name;

    private String system;

    private int fuel;

    private CarrierServices services;
    
    private Orders orders;
    
    private CarrierMarket market;

    /**
     * {@inheritDoc}
     */
    @Override
    public DiffMap diff(IFleetCarrier other) {
        return DiffMap
            .Builder()
                .append("name", this.name, other.getName())
                .append("fuel", this.fuel, other.getFuel())
                .append("system", this.system, other.getSystem())
                .appendDiff("services", this.services.diff(other.getServices()))
                .appendDiff("orders", DiffMapper.build(this.orders, other.getOrders()))
                .build();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public DefinedTable getTable() {
        return DefinedTable.CARRIERS;
    }
}
