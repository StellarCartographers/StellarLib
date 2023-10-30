package space.tscg.api.carrier;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import elite.dangerous.fdevid.Outfitting.Category;
import elite.dangerous.fdevid.Outfitting.Guidance;
import elite.dangerous.fdevid.Outfitting.Mount;
import elite.dangerous.fdevid.Outfitting.Rating;
import space.tscg.database.entity.CarrierMarket.CarrierModule;

@JsonDeserialize(as = CarrierModule.class)
public interface ICarrierModule
{
    int getFdevId();
    
    Category getCategory();
    
    String getName();
    
    Mount getMount();
    
    Guidance getGuidance();
    
    int getModuleClass();
    
    Rating getRating();
    
    int getCost();
    
    int getStock();
}