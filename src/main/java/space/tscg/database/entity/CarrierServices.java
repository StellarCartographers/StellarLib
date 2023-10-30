/**
 * Copyright (c) 2023  The Stellar Cartographers' Guild.
 *
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */
package space.tscg.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import space.tscg.api.Diffable;
import space.tscg.api.carrier.ICarrierServices;
import space.tscg.api.carrier.IService;
import space.tscg.api.carrier.ITaxableService;
import space.tscg.collections.DiffMap;

@Getter
@Builder(builderMethodName = "Builder")
@Jacksonized
public class CarrierServices implements ICarrierServices, Diffable<CarrierServices>
{
    @Builder.Default
    private TaxableService refuel = null;

    @Builder.Default
    private TaxableService repair = null;

    @Builder.Default
    private TaxableService armoury = null;

    @Builder.Default
    private TaxableService shipyard = null;

    @Builder.Default
    private TaxableService outfitting = null;
    
    @Builder.Default
    private TaxableService pioneerSupplies = null;
    
    @Builder.Default
    private Service redemptionOffice = null;

    @Builder.Default
    private Service secureWarehouse = null;

    @Builder.Default
    private Service universalCartographics = null;

    @Builder.Default
    private Service concourseBar = null;

    @Builder.Default
    private Service vistaGenomics = null;

    @Override
    @JsonIgnore
    public boolean isRefuelInstalled()
    {
        return this.refuel != null;
    }

    @Override
    @JsonIgnore
    public boolean isRepairInstalled()
    {
        return this.repair != null;
    }

    @Override
    @JsonIgnore
    public boolean isArmouryInstalled()
    {
        return this.armoury != null;
    }

    @Override
    @JsonIgnore
    public boolean isRedemptionOfficeInstalled()
    {
        return this.redemptionOffice != null;
    }

    @Override
    @JsonIgnore
    public boolean isShipyardInstalled()
    {
        return this.shipyard != null;
    }

    @Override
    @JsonIgnore
    public boolean isOutfittingInstalled()
    {
        return this.outfitting != null;
    }

    @Override
    @JsonIgnore
    public boolean isSecureWarehouseInstalled()
    {
        return this.secureWarehouse != null;
    }

    @Override
    @JsonIgnore
    public boolean isUniversalCartographicsInstalled()
    {
        return this.universalCartographics != null;
    }

    @Override
    @JsonIgnore
    public boolean isConcourseBarInstalled()
    {
        return this.concourseBar != null;
    }

    @Override
    @JsonIgnore
    public boolean isVistaGenomicsInstalled()
    {
        return this.vistaGenomics != null;
    }

    @Override
    @JsonIgnore
    public boolean isPioneerSuppliesInstalled()
    {
        return this.pioneerSupplies != null;
    }

    @Override
    public DiffMap diff(CarrierServices other)
    {
        var diff = DiffMap.Builder();
        if(this.refuel != null)
            diff.appendDiff("refuel", this.refuel.diff(other.getRefuel()));
        if(this.repair != null)
            diff.appendDiff("repair", this.repair.diff(other.getRepair()));
        if(this.armoury != null)
            diff.appendDiff("armoury", this.armoury.diff(other.getArmoury()));
        if(this.redemptionOffice != null)
            diff.appendDiff("redemptionOffice", this.redemptionOffice.diff(other.getRedemptionOffice()));
        if(this.shipyard != null)
            diff.appendDiff("shipyard", this.shipyard.diff(other.getShipyard()));
        if(this.outfitting != null)
            diff.appendDiff("outfitting", this.outfitting.diff(other.getOutfitting()));
        if(this.secureWarehouse != null)
            diff.appendDiff("secureWarehouse", this.secureWarehouse.diff(other.getSecureWarehouse()));
        if(this.universalCartographics != null)
            diff.appendDiff("universalCartographics", this.universalCartographics.diff(other.getUniversalCartographics()));
        if(this.concourseBar != null)
            diff.appendDiff("concourseBar", this.concourseBar.diff(other.getConcourseBar()));
        if(this.vistaGenomics != null)
            diff.appendDiff("vistaGenomics", this.vistaGenomics.diff(other.getVistaGenomics()));
        if(this.pioneerSupplies != null)
            diff.appendDiff("pioneerSupplies", this.pioneerSupplies.diff(other.getPioneerSupplies()));
                
        return diff.build();
    }
    
    @Getter
    @Builder(builderMethodName = "Creator")
    @Jacksonized
    public static class Service implements IService, Diffable<Service> {
        private boolean enabled;
        
        @Override
        public DiffMap diff(Service other) {
            return DiffMap
                .Builder()
                    .append("active", this.isEnabled(), other.isEnabled())
                    .build();
        }
    }
    
    @Getter
    @Builder(builderMethodName = "Creator")
    @Jacksonized
    public static class TaxableService implements ITaxableService, Diffable<TaxableService> {
        private boolean enabled;
        private int tax;
        
        @Override
        public DiffMap diff(TaxableService other) {
            return DiffMap
                .Builder()
                    .append("enabled", this.isEnabled(), other.isEnabled())
                    .append("tax", this.getTax(), other.getTax())
                    .build();
        }
    }
}
