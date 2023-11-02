package space.tscg.api.carrier;

import java.util.ArrayList;
import java.util.List;

import elite.dangerous.capi.FleetCarrierData;
import elite.dangerous.capi.modal.fleetcarrier.Modules;
import elite.dangerous.capi.modal.fleetcarrier.Modules.EliteModule;
import elite.dangerous.capi.modal.fleetcarrier.Ships;
import elite.dangerous.capi.modal.fleetcarrier.Ships.EliteShip;
import elite.dangerous.fdevid.Outfitting;
import elite.dangerous.fdevid.Shipyard;
import space.tscg.database.entity.CarrierMarket;
import space.tscg.database.entity.CarrierMarket.CarrierModule;
import space.tscg.database.entity.CarrierMarket.CarrierShip;
import space.tscg.database.entity.CarrierServices;
import space.tscg.database.entity.CarrierServices.Service;
import space.tscg.database.entity.CarrierServices.TaxableService;
import space.tscg.database.entity.FleetCarrier;

final class Builder {
    /**
     * Creates a FleetCarrier instance from the data returned from Frontier's CAPI /fleetcarrier endpoint
     *
     * @param data the CAPIFleetCarrier
     * @return a new FleetCarrier instance
     */
    static FleetCarrier build(FleetCarrierData data) {
        var services = data.getServicesCrew();
        var taxation = data.getFinance().getServiceTaxation();
        
        var moduleList = buildModuleList(data.getModules());
        var shipList = buildShipList(data.getShips());
        
        var cs = CarrierServices.Builder();
        if(services.isRefuelInstalled())
            cs.refuel(TaxableService.Creator().enabled(!services.isRefuelSuspended()).tax(taxation.refuel).build());
        if(services.isRepairInstalled())
            cs.repair(TaxableService.Creator().enabled(!services.isRepairSuspended()).tax(taxation.repair).build());
        if(services.isRearmInstalled())
            cs.armoury(TaxableService.Creator().enabled(!services.isRearmSuspended()).tax(taxation.rearm).build());
        if(services.isRedemptionOfficeInstalled())
            cs.redemptionOffice(Service.Creator().enabled(!services.isRedemptionOfficeSuspended()).build());
        if(services.isShipyardInstalled())
            cs.shipyard(TaxableService.Creator().enabled(!services.isShipyardSuspended()).tax(taxation.shipyard).build());
        if(services.isOutfittingInstalled())
            cs.outfitting(TaxableService.Creator().enabled(!services.isOutfittingSuspended()).tax(taxation.outfitting).build());
        if(services.isBlackmarketInstalled())
            cs.secureWarehouse(Service.Creator().enabled(!services.isBlackmarketSuspended()).build());
        if(services.isUniversalCartographicsInstalled())
            cs.universalCartographics(Service.Creator().enabled(!services.isUniversalCartographicsSuspended()).build());
        if(services.isBartenderInstalled())
            cs.concourseBar(Service.Creator().enabled(!services.isBartenderSuspended()).build());
        if(services.isVistaGenomicsInstalled())
            cs.vistaGenomics(Service.Creator().enabled(!services.isVistaGenomicsSuspended()).build());
        if(services.isPioneerSuppliesInstalled())
            cs.pioneerSupplies(TaxableService.Creator().enabled(!services.isPioneerSuppliesSuspended()).tax(taxation.pioneersupplies).build());
            
        return FleetCarrier.Builder()
                .id(data.getCarrierId())
                .callsign(data.getCallsign())
                .name(data.getName())
                .system(data.getCurrentStarSystem())
                .fuel(data.getFuel())
                .services(cs.build())
                .orders(data.getOrders())
                .market(CarrierMarket.Builder().ships(shipList).modules(moduleList).build())
                .build();
    }
    
    private static List<ICarrierModule> buildModuleList(Modules modules)
    {
        List<ICarrierModule> list = new ArrayList<>();
        if(modules.isEmpty())
        {
            return list;
        } else {
            for(EliteModule module : modules)
            {
                Outfitting.Module m = Outfitting.getFromId(module.getId());
                
                var builder = CarrierModule.Builder();
                if(m.getGuidance() != null)
                    builder.guidance(m.getGuidance());
                if(m.getMount() != null)
                    builder.mount(m.getMount());
                
                builder.fdevId(module.getId());
                builder.stock(module.getStock());
                builder.cost(module.getCost());
                builder.category(m.getCategory());
                builder.moduleClass(m.getModuleClass());
                builder.name(m.getName());
                builder.rating(m.getRating());

                list.add(builder.build());
            }
            
            return list;
        }
    }
    
    private static List<ICarrierShip> buildShipList(Ships ships)
    {
        List<ICarrierShip> list = new ArrayList<>();
        if(ships.getShipyard_list().isEmpty())
        {
            return list;
        } else {
            for(EliteShip ship: ships.getShipyard_list())
            {
                list.add(CarrierShip.Builder()
                    .fdevId(ship.getId())
                    .cost(ship.getBaseValue())
                    .name(Shipyard.getFromId(ship.getId()).getName())
                    .build());
            }
            
            return list;
        }
    }
}