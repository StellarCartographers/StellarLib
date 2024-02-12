package space.tscg.carrrier;

public class NonInstalledService
{
    public static Service Service() {
        return Service.Creator().enabled(false).build();
    }
    
    public static TaxableService TaxableService() {
        return TaxableService.Creator().enabled(false).taxRate(0).build();
    }
}
