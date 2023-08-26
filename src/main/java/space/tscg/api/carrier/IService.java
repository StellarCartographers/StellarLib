package space.tscg.api.carrier;

/**
 * A toggleable service that is accessible on fleetcarriers
 */
public interface IService
{
    /**
     * If this service is installed and publically available
     * 
     * @return TRUE if, and only if, the service is PUBLIC
     */   
    boolean isEnabled();
}
