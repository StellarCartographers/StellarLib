package space.tscg.api.carrier;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import space.tscg.database.entity.CarrierMarket.CarrierShip;

@JsonDeserialize(as = CarrierShip.class)
public interface ICarrierShip
{
    int getFdevId();
    
    String getName();
    
    int getCost();
}