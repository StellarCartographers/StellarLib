package space.tscg.collections.decorator;

import java.util.Set;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class SetDecorator<E> extends CollectionDecorator<E> implements Set<E>
{
    private static final long serialVersionUID = -3096807767457886899L;

    protected SetDecorator(final Set<E> set)
    {
        super(set);
    }

    @Override
    protected Set<E> decorated()
    {
        return (Set<E>) super.decorated();
    }

    @Override
    public boolean equals(final Object object)
    {
        return object == this || decorated().equals(object);
    }

    @Override
    public int hashCode()
    {
        return decorated().hashCode();
    }
}
