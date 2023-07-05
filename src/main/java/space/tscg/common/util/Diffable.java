package space.tscg.common.util;


/**
 * Classes that inherit Diffable can have another instance of their type
 * passed in to check for the differences between the two
 *
 * @param <T> This must be the class thats implementing this interface
 */
public interface Diffable<T>
{

    /**
     * Generates a Diff style UpdatedValues class that acts as a Map only
     * containing fields or ojects that changed during an operation.
     *
     * @param other The same type to compare too
     * @return an UpdatedValues for this type
     */
    UpdatedValues diff(T other);
}
