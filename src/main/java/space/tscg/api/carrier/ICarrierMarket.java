package space.tscg.api.carrier;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import space.tscg.database.entity.CarrierMarket;

@JsonDeserialize(as = CarrierMarket.class)
public interface ICarrierMarket
{
    List<ICarrierShip> getShips();

    List<ICarrierModule> getModules();
}
