package space.tscg.database.entity;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import elite.dangerous.fdevid.Outfitting.Category;
import elite.dangerous.fdevid.Outfitting.Guidance;
import elite.dangerous.fdevid.Outfitting.Mount;
import elite.dangerous.fdevid.Outfitting.Rating;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import space.tscg.api.carrier.ICarrierMarket;
import space.tscg.api.carrier.ICarrierModule;
import space.tscg.api.carrier.ICarrierShip;

@Getter
@Builder(builderMethodName = "Builder")
@Jacksonized
public class CarrierMarket implements ICarrierMarket
{
    private List<ICarrierShip>   ships;
    private List<ICarrierModule> modules;

    @Getter
    @Builder(builderMethodName = "Builder")
    @Jacksonized
    public static class CarrierShip implements ICarrierShip
    {
        private int fdevId;
        private String name;
        private int    cost;
    }

    @Getter
    @Builder(builderMethodName = "Builder")
    @Jacksonized
    public static class CarrierModule implements ICarrierModule
    {
        private int fdevId;
        private Category category;
        private String   name;
        @Nullable
        @Builder.Default
        private Mount    mount = null;
        @Nullable
        @Builder.Default
        private Guidance guidance = null;
        private int      moduleClass;
        private Rating   rating;
        private int      cost;
        private int      stock;
    }
}
