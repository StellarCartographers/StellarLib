/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.carrrier;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import com.fasterxml.jackson.annotation.JsonIgnore;

import space.tscg.api.carrier.ICarrierServices;
import space.tscg.collections.DiffMap;

@Getter@Builder(builderMethodName = "Builder")
@Jacksonized
public class ServicesImpl implements ICarrierServices
{
    @Builder.Default
    private TaxableService refuel                 = null;
    @Builder.Default
    private TaxableService repair                 = null;
    @Builder.Default
    private TaxableService armoury                = null;
    @Builder.Default
    private TaxableService shipyard               = null;
    @Builder.Default
    private TaxableService outfitting             = null;
    @Builder.Default
    private TaxableService pioneerSupplies        = null;
    @Builder.Default
    private Service        redemptionOffice       = null;
    @Builder.Default
    private Service        secureWarehouse        = null;
    @Builder.Default
    private Service        universalCartographics = null;
    @Builder.Default
    private Service        concourseBar           = null;
    @Builder.Default
    private Service        vistaGenomics          = null;

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
    public DiffMap diff(ICarrierServices other)
    {
        var diff = DiffMap.Builder();
        if (this.refuel != null)
            diff.appendDiff("refuel", this.refuel.diff(other.refuel()));
        if (this.repair != null)
            diff.appendDiff("repair", this.repair.diff(other.repair()));
        if (this.armoury != null)
            diff.appendDiff("armoury", this.armoury.diff(other.armoury()));
        if (this.redemptionOffice != null)
            diff.appendDiff("redemptionOffice", this.redemptionOffice.diff(other.redemptionOffice()));
        if (this.shipyard != null)
            diff.appendDiff("shipyard", this.shipyard.diff(other.shipyard()));
        if (this.outfitting != null)
            diff.appendDiff("outfitting", this.outfitting.diff(other.outfitting()));
        if (this.secureWarehouse != null)
            diff.appendDiff("secureWarehouse", this.secureWarehouse.diff(other.secureWarehouse()));
        if (this.universalCartographics != null)
            diff.appendDiff("universalCartographics", this.universalCartographics.diff(other.universalCartographics()));
        if (this.concourseBar != null)
            diff.appendDiff("concourseBar", this.concourseBar.diff(other.concourseBar()));
        if (this.vistaGenomics != null)
            diff.appendDiff("vistaGenomics", this.vistaGenomics.diff(other.vistaGenomics()));
        if (this.pioneerSupplies != null)
            diff.appendDiff("pioneerSupplies", this.pioneerSupplies.diff(other.pioneerSupplies()));
        return diff.build();
    }
}
