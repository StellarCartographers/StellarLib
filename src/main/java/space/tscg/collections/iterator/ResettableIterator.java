package space.tscg.collections.iterator;

import java.util.Iterator;

public interface ResettableIterator<E> extends Iterator<E>
{
    /**
     * Resets the iterator back to the position at which the iterator
     * was created.
     */
    void reset();
}
