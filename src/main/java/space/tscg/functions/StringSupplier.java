package space.tscg.functions;

@FunctionalInterface
public interface StringSupplier<T>
{
    String apply(T t);
}
