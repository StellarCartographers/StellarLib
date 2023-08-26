package space.tscg.api.carrier;

/**
 * Represents the collection of Services a FleetCarrier can have
 */
public interface ICarrierServices
{
    
    /**
     * Returns the refuel Service
     *
     * @return ITaxableService
     */
    ITaxableService getRefuel();

    /**
     * Returns the repair Service.
     *
     * @return ITaxableService
     */
    ITaxableService getRepair();

    /**
     * Returns the armoury Service.
     *
     * @return ITaxableService
     */
    ITaxableService getArmoury();

    /**
     * Returns the redemption office Service.
     *
     * @return IService
     */
    IService getRedemptionOffice();

    /**
     * Returns the shipyard Service.
     *
     * @return ITaxableService
     */
    ITaxableService getShipyard();

    /**
     * Returns the outfitting Service.
     *
     * @return ITaxableService
     */
    ITaxableService getOutfitting();

    /**
     * Returns the secure warehouse Service.
     *
     * @return IService
     */
    IService getSecureWarehouse();

    /**
     * Returns the universal cartographics Service.
     *
     * @return IService
     */
    IService getUniversalCartographics();

    /**
     * Returns the concourse bar Service.
     *
     * @return IService
     */
    IService getConcourseBar();

    /**
     * Returns the vista genomics Service.
     *
     * @return IService
     */
    IService getVistaGenomics();

    /**
     * Returns the pioneer supplies Service.
     *
     * @return ITaxableService
     */
    ITaxableService getPioneerSupplies();
}
