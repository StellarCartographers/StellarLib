/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * 
 * Copyright (C) 2023 StellarCartographers.
 * 
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.api.carrier;

import java.util.*;

import elite.dangerous.capi.FleetCarrierData;
import elite.dangerous.capi.modal.fc.*;
import elite.dangerous.capi.modal.fc.Modules.CarrierModule;
import elite.dangerous.fdev.Outfitting;

import space.tscg.carrrier.*;
import space.tscg.carrrier.Service;
import space.tscg.database.entity.FleetCarrier;

public class Builder
{
    /**
     * Creates a FleetCarrier instance from the data returned from Frontier's CAPI /fleetcarrier endpoint
     *
     * @param  data
     *                  the CAPIFleetCarrier
     * 
     * @return      a new FleetCarrier instance
     */
    static FleetCarrier build(FleetCarrierData data)
    {
        var taxation = data.finance().serviceTaxation();
        var moduleList = buildModuleList(data.modules());
        var shipList = buildShipList(data.ships());
        var cs = ServicesImpl.Builder();
        if (data.isRefuelInstalled())
            cs.refuel(TaxableService.Creator().enabled(!data.isRefuelSuspended()).taxRate(taxation.refuel).build());
        else
            cs.refuel(NonInstalledService.TaxableService());
        
        if (data.isRepairInstalled())
            cs.repair(TaxableService.Creator().enabled(!data.isRepairSuspended()).taxRate(taxation.repair).build());
        else
            cs.repair(NonInstalledService.TaxableService());
        
        if (data.isRearmInstalled())
            cs.armoury(TaxableService.Creator().enabled(!data.isRearmSuspended()).taxRate(taxation.rearm).build());
        else
            cs.armoury(NonInstalledService.TaxableService());
        
        if (data.isRedemptionOfficeInstalled())
            cs.redemptionOffice(Service.Creator().enabled(!data.isRedemptionOfficeSuspended()).build());
        else
            cs.redemptionOffice(NonInstalledService.Service());
        
        if (data.isShipyardInstalled())
            cs.shipyard(TaxableService.Creator().enabled(!data.isShipyardSuspended()).taxRate(taxation.shipyard).build());
        else
            cs.shipyard(NonInstalledService.TaxableService());
        
        if (data.isOutfittingInstalled())
            cs.outfitting(TaxableService.Creator().enabled(!data.isOutfittingSuspended()).taxRate(taxation.outfitting).build());
        else
            cs.outfitting(NonInstalledService.TaxableService());
        
        if (data.isBlackmarketInstalled())
            cs.secureWarehouse(Service.Creator().enabled(!data.isBlackmarketSuspended()).build());
        else
            cs.secureWarehouse(NonInstalledService.Service());
        
        if (data.isUniversalCartographicsInstalled())
            cs.universalCartographics(Service.Creator().enabled(!data.isUniversalCartographicsSuspended()).build());
        else
            cs.universalCartographics(NonInstalledService.Service());
        
        if (data.isBartenderInstalled())
            cs.concourseBar(Service.Creator().enabled(!data.isBartenderSuspended()).build());
        else
            cs.concourseBar(NonInstalledService.Service());
        
        if (data.isVistaGenomicsInstalled())
            cs.vistaGenomics(Service.Creator().enabled(!data.isVistaGenomicsSuspended()).build());
        else
            cs.vistaGenomics(NonInstalledService.Service());
        
        if (data.isPioneerSuppliesInstalled())
            cs.pioneerSupplies(TaxableService.Creator().enabled(!data.isPioneerSuppliesSuspended()).taxRate(taxation.pioneersupplies).build());
        else
            cs.pioneerSupplies(NonInstalledService.TaxableService());
        
        return FleetCarrier.Builder().id(data.carrierId().asText()).callsign(data.callsign()).name(data.name()).system(data.currentStarSystem()).fuel(data.fuel()).services(cs.build()).orders(data.orders()).market(MarketImpl.Builder().ships(shipList).modules(moduleList).build())
                        .build();
    }

    private static List<ICarrierModule> buildModuleList(Modules modules)
    {
        List<ICarrierModule> list = new ArrayList<>();
        if (modules.isEmpty())
        {
            return list;
        } else
        {
            for (CarrierModule module : modules)
            {
                Outfitting.Module m = Outfitting.getFromId(module.id().asLong());
                var builder = ModuleImpl.Builder();
                if (m.guidance() != null)
                    builder.guidance(m.guidance());
                if (m.mount() != null)
                    builder.mount(m.mount());
                //builder.id(module.id());
                builder.stock(module.stock());
                builder.cost(module.cost());
                builder.category(m.category());
                builder.moduleClass(m.moduleClass());
                builder.name(m.name());
                builder.rating(m.rating());
                list.add(builder.build());
            }
            return list;
        }
    }

    private static List<ICarrierShip> buildShipList(Ships ships)
    {
        List<ICarrierShip> list = new ArrayList<>();
//        if (ships.shipyard_list().isEmpty())
//        {
//            return list;
//        } else
//        {
//            for (CarrierShip ship : ships.shipyard_list())
//            {
//                list.add(ShipImpl.Builder().id(ship.id()).cost(ship.baseValue()).name(Shipyard.getFromId(ship.id().toLong().intValue()).name()).build());
//            }
            return list;
//        }
    }
}
