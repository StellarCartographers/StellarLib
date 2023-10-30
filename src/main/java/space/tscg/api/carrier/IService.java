package space.tscg.api.carrier;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import space.tscg.database.entity.CarrierServices.Service;

/**
 * A toggleable service that is accessible on fleetcarriers
 */
@JsonDeserialize(as = Service.class)
public interface IService
{
    /**
     * If this service is installed and publically available
     * 
     * @return TRUE if, and only if, the service is PUBLIC
     */   
    boolean isEnabled();
}
