package space.tscg.api.carrier;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import space.tscg.database.entity.CarrierServices.TaxableService;

/**
 * A tax-rate configurable service that is accessible on fleetcarriers 
 */
@JsonDeserialize(as = TaxableService.class)
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
