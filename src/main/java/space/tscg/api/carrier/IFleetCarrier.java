/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.api.carrier;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import elite.dangerous.capi.FleetCarrierData;
import elite.dangerous.capi.modal.fc.orders.Orders;

import space.tscg.api.Diffable;
import space.tscg.api.database.DbEntity;
import space.tscg.database.entity.FleetCarrier;

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
    String id();

    /**
     * Returns the Carrier's callsign.
     *
     * @return {@code Carrier Callsign}
     */
    String callsign();

    /**
     * Returns the carrier's name.
     *
     * @return the carrier's name
     */
    String name();

    /**
     * Returns last known system the carrier was located in
     *
     * @return the current system
     */
    String system();

    /**
     * Returns the last known Tritium fuel count
     *
     * @return fuel count
     */
    int fuel();

    /**
     * Returns an instance of {@link ICarrierServices}
     *
     * @return ICarrierServices instance
     */
    ICarrierServices services();

    /**
     * Returns an instance of {@link Orders}
     *
     * @return Orders instance
     */
    Orders orders();

    /**
     * Returns an instance of {@link ICarrierMarket}
     *
     * @return ICarrierMarket instance
     */
    ICarrierMarket market();

    static IFleetCarrier fromFleetCarrierData(FleetCarrierData data)
    {
        return Builder.build(data);
    }
}
