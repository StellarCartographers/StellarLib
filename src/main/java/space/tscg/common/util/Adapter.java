/**
 * Copyright (C) 2023  The Stellar Cartographers' Guild
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package space.tscg.common.util;

import elite.dangerous.capi.FleetCarrierResult;
import lombok.experimental.UtilityClass;
import space.tscg.common.entity.FleetCarrier;
import space.tscg.common.entity.FleetCarrier.Services;
import space.tscg.common.entity.FleetCarrier.Services.Armoury;
import space.tscg.common.entity.FleetCarrier.Services.ConcourseBar;
import space.tscg.common.entity.FleetCarrier.Services.Outfitting;
import space.tscg.common.entity.FleetCarrier.Services.PioneerSupplies;
import space.tscg.common.entity.FleetCarrier.Services.RedemptionOffice;
import space.tscg.common.entity.FleetCarrier.Services.Refuel;
import space.tscg.common.entity.FleetCarrier.Services.Repair;
import space.tscg.common.entity.FleetCarrier.Services.SecureWarehouse;
import space.tscg.common.entity.FleetCarrier.Services.Shipyard;
import space.tscg.common.entity.FleetCarrier.Services.UniversalCartographics;
import space.tscg.common.entity.FleetCarrier.Services.VistaGenomics;

/**
 * A class that holds generalized "adapter" methods that
 * take in A and create an instnace of B
 */
@UtilityClass
public class Adapter
{

    /**
     * Creates a FleetCarrier instance from the data returned from Fontiers CAPI /fleetcarrier endpoint
     *
     * @param carrier the FleetCarrierResult
     * @return a new FleetCarrier instance
     */
    public static FleetCarrier fromCapiData(FleetCarrierResult carrier)
    {
        //@noformat
        return FleetCarrier.Builder()
            .carrierId(carrier.getMarket().getId())
            .callsign(carrier.getName().getCallsign())
            .vanityName(carrier.getName().getVanityName())
            .currentStarSystem(carrier.getCurrentStarSystem())
            .fuel(Integer.valueOf(carrier.getFuel()))
            .services(Services.Builder()
                .refuel(Refuel.Builder()
                    .active(carrier.isRefuelEnabled())
                    .taxRate(carrier.getFinance().getServiceTaxation().refuel)
                    .build())
                .repair(Repair.Builder()
                    .active(carrier.isRepairEnabled())
                    .taxRate(carrier.getFinance().getServiceTaxation().repair)
                    .build())
                .armoury(Armoury.Builder()
                    .active(carrier.isRearmEnabled())
                    .taxRate(carrier.getFinance().getServiceTaxation().rearm)
                    .build())
                .redemptionOffice(RedemptionOffice.Builder()
                    .active(carrier.isRedemptionOfficeEnabled())
                    .build())
                .shipyard(Shipyard.Builder()
                    .active(carrier.isShipyardEnabled())
                    .taxRate(carrier.getFinance().getServiceTaxation().shipyard)
                    .build())
                .outfitting(Outfitting.Builder()
                    .active(carrier.isOutfittingEnabled())
                    .taxRate(carrier.getFinance().getServiceTaxation().outfitting)
                    .build())
                .secureWarehouse(SecureWarehouse.Builder()
                    .active(carrier.isBlackmarketEnabled())
                    .build())
                .universalCartographics(UniversalCartographics.Builder()
                    .active(carrier.isUniversalCartographicsEnabled())
                    .build())
                .concourseBar(ConcourseBar.Builder()
                    .active(carrier.isConcourseEnabled())
                    .build())
                .vistaGenomics(VistaGenomics.Builder()
                    .active(carrier.isVistaGenomicsEnabled())
                    .build())
                .pioneerSupplies(PioneerSupplies.Builder()
                    .active(carrier.isPioneerSuppliesEnabled())
                    .taxRate(carrier.getFinance().getServiceTaxation().pioneersupplies)
                    .build())
                .build()
                )
            .build();
        //@format
    }
}
