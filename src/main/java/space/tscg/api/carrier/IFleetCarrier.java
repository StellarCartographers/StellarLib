package space.tscg.api.carrier;

/**
 * Represents very basic information about a Fleet Carrier, all implementations should be able to return
 * all the information contained in this interface.
 */
public interface IFleetCarrier
{
    /**
     * Returns the carrier's id. This is the Market ID for the carrier
     *
     * @return {@code Carrier ID, aka Carrier Market ID}
     */
    String getCarrierId();
    
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
     * Returns an instance of {@link ICarrierServices}, holds all possible Services a carrier can have enabled
     *
     * @return the services
     */
    ICarrierServices getServices();
}
