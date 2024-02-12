/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.api.carrier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import space.tscg.api.Diffable;
import space.tscg.carrrier.ServicesImpl;

/**
 * Represents the collection of Services a FleetCarrier can have
 */
@JsonDeserialize(as = ServicesImpl.class)
public interface ICarrierServices extends Diffable<ICarrierServices>
{
    /**
     * Returns the refuel Service
     *
     * @return ITaxableService
     */
    ITaxableService refuel();

    /**
     * If this service is installed
     * 
     * @return true if installed, false if not
     */
    @JsonIgnore
    boolean isRefuelInstalled();

    /**
     * Returns the repair Service.
     *
     * @return ITaxableService
     */
    ITaxableService repair();

    /**
     * If this service is installed
     * 
     * @return true if installed, false if not
     */
    @JsonIgnore
    boolean isRepairInstalled();

    /**
     * Returns the armoury Service.
     *
     * @return ITaxableService
     */
    ITaxableService armoury();

    /**
     * If this service is installed
     * 
     * @return true if installed, false if not
     */
    @JsonIgnore
    boolean isArmouryInstalled();

    /**
     * Returns the redemption office Service.
     *
     * @return IService
     */
    IService redemptionOffice();

    /**
     * If this service is installed
     * 
     * @return true if installed, false if not
     */
    @JsonIgnore
    boolean isRedemptionOfficeInstalled();

    /**
     * Returns the shipyard Service.
     *
     * @return ITaxableService
     */
    ITaxableService shipyard();

    /**
     * If this service is installed
     * 
     * @return true if installed, false if not
     */
    @JsonIgnore
    boolean isShipyardInstalled();

    /**
     * Returns the outfitting Service.
     *
     * @return ITaxableService
     */
    ITaxableService outfitting();

    /**
     * If this service is installed
     * 
     * @return true if installed, false if not
     */
    @JsonIgnore
    boolean isOutfittingInstalled();

    /**
     * Returns the secure warehouse Service.
     *
     * @return IService
     */
    IService secureWarehouse();

    /**
     * If this service is installed
     * 
     * @return true if installed, false if not
     */
    @JsonIgnore
    boolean isSecureWarehouseInstalled();

    /**
     * Returns the universal cartographics Service.
     *
     * @return IService
     */
    IService universalCartographics();

    /**
     * If this service is installed
     * 
     * @return true if installed, false if not
     */
    @JsonIgnore
    boolean isUniversalCartographicsInstalled();

    /**
     * Returns the concourse bar Service.
     *
     * @return IService
     */
    IService concourseBar();

    /**
     * If this service is installed
     * 
     * @return true if installed, false if not
     */
    @JsonIgnore
    boolean isConcourseBarInstalled();

    /**
     * Returns the vista genomics Service.
     *
     * @return IService
     */
    IService vistaGenomics();

    /**
     * If this service is installed
     * 
     * @return true if installed, false if not
     */
    @JsonIgnore
    boolean isVistaGenomicsInstalled();

    /**
     * Returns the pioneer supplies Service.
     *
     * @return ITaxableService
     */
    ITaxableService pioneerSupplies();

    /**
     * If this service is installed
     * 
     * @return true if installed, false if not
     */
    @JsonIgnore
    boolean isPioneerSuppliesInstalled();
}
