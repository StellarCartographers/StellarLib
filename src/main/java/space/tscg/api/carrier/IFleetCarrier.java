package space.tscg.api.carrier;

import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import elite.dangerous.capi.FleetCarrierData;
import elite.dangerous.capi.modal.fleetcarrier.Orders;
import net.dv8tion.jda.api.entities.UserSnowflake;
import space.tscg.api.Diffable;
import space.tscg.api.database.DbEntity;
import space.tscg.database.DefinedTable;
import space.tscg.database.defined.TSCGDatabase;
import space.tscg.database.entity.FleetCarrier;
import space.tscg.database.entity.TSCGMember;

/**
 * Represents very basic information about a Fleet Carrier, all implementations should be able to return
 * all the information contained in this interface.
 */
@JsonDeserialize(as = FleetCarrier.class)
public interface IFleetCarrier extends DbEntity, Diffable<IFleetCarrier>
{
    /**
     * Returns the carrier's id. This is the Market ID for the carrier
     *
     * @return {@code Carrier ID, aka Carrier Market ID}
     */
    String getId();
    
    /**
     * Returns the Carrier's callsign.
     *
     * @return {@code Carrier Callsign}
     */
    String getCallsign();
    
    /**
     * Returns the carrier's name.
     *
     * @return the carrier's name
     */
    String getName();
    
    /**
     * Returns last known system the carrier was located in
     *
     * @return the current system
     */
    String getSystem();
    
    /**
     * Returns the last known Tritium fuel count
     *
     * @return fuel count
     */
    int getFuel();
    
    /**
     * Returns an instance of {@link ICarrierServices}
     *
     * @return ICarrierServices instance
     */
    ICarrierServices getServices();
    
    /**
     * Returns an instance of {@link Orders}
     *
     * @return Orders instance
     */
    Orders getOrders();
    
    /**
     * Returns an instance of {@link ICarrierMarket}
     *
     * @return ICarrierMarket instance
     */
    ICarrierMarket getMarket();
    
    static IFleetCarrier fromFleetCarrierData(FleetCarrierData data)
    {
        return Builder.build(data);
    }
    
    static Optional<IFleetCarrier> fromId(String id)
    {
        return Optional.ofNullable(TSCGDatabase.instance().get(DefinedTable.CARRIERS, id, IFleetCarrier.class));
    }
    
    static IFleetCarrier fromMmember(TSCGMember member)
    {
        return TSCGDatabase.instance().get(DefinedTable.CARRIERS, member.getElite().getCarrierId(), IFleetCarrier.class);
    }
    
    static IFleetCarrier fromUserSnowflake(UserSnowflake snowflake)
    {
        return TSCGDatabase.instance().get(DefinedTable.CARRIERS, TSCGMember.fromUserSnowflake(snowflake).getElite().getCarrierId(), IFleetCarrier.class);
    }
}
