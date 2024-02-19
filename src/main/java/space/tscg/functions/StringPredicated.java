package space.tscg.functions;

public class StringPredicated
{
    public static PredicatedMonoFunction<String> startsAndOrEndsWith(String startAndEnd, FunctionSupplier<String> function)
    {
        return startsAndOrEndsWith(startAndEnd, startAndEnd, function);
    }
    
    public static PredicatedMonoFunction<String> startsAndOrEndsWith(String startsWith, String endsWith, FunctionSupplier<String> function)
    {
        return PredicatedMonoFunction.of(t -> t.startsWith(startsWith) || t.endsWith(endsWith), function);
    }
}
