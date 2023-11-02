package space.tscg.api.carrier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import space.tscg.api.Diffable;
import space.tscg.database.entity.CarrierServices;

/**
 * Represents the collection of Services a FleetCarrier can have
 */
@JsonDeserialize(as = CarrierServices.class)
public interface ICarrierServices extends Diffable<ICarrierServices>
{
    
    /**
     * Returns the refuel Service
     *
     * @return ITaxableService
     */
    ITaxableService getRefuel();
    
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
    ITaxableService getRepair();

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
    ITaxableService getArmoury();
    
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
    IService getRedemptionOffice();
    
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
    ITaxableService getShipyard();
    
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
    ITaxableService getOutfitting();

    
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
    IService getSecureWarehouse();

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
    IService getUniversalCartographics();

    
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
    IService getConcourseBar();

    
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
    IService getVistaGenomics();

    
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
    ITaxableService getPioneerSupplies();
    
    
    /**
     * If this service is installed
     * 
     * @return true if installed, false if not
     */
    @JsonIgnore
    boolean isPioneerSuppliesInstalled();
}
