package space.tscg.api.carrier;

/**
 * A tax-rate configurable service that is accessible on fleetcarriers 
 */
public interface ITaxableService
{
    /**
     * If this service is installed and publically available
     * 
     * @return TRUE if, and only if, the service is PUBLIC
     */   
    boolean isEnabled();
    
    int getTax();
}
