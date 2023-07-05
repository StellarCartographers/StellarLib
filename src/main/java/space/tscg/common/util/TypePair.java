package space.tscg.common.util;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class TypePair<TYPE extends Diffable<TYPE>>
{

    /** The old type. */
    private final TYPE oldType;

    /** The new type. */
    private final TYPE newType;

    /**
     * Instantiates a new type pair.
     *
     * @param oldType the old type
     * @param newType the new type
     */
    public TypePair(TYPE oldType, TYPE newType)
    {
        this.oldType = oldType;
        this.newType = newType;
    }

    /**
     * Instantiates a new type pair.
     *
     * @param typeBuilder the type builder
     */
    private TypePair(Builder<TYPE> typeBuilder)
    {
        this.oldType = typeBuilder.list.get(0);
        this.newType = typeBuilder.list.get(1);
    }

    /**
     * Gets the diff.
     *
     * @return the diff
     */
    public UpdatedValues getDiff()
    {
        return newType.diff(oldType);
    }

    /**
     * Builder.
     *
     * @param <TYPE> the generic type
     * @return the builder
     */
    public static <TYPE extends Diffable<TYPE>> Builder<TYPE> Builder()
    {
        return new Builder<>();
    }

    /**
     * Instantiates a new builder.
     *
     * @param <TYPE> the generic type
     */

    /**
     * Instantiates a new builder.
     */
    @NoArgsConstructor
    public static class Builder<TYPE extends Diffable<TYPE>> {

        private List<TYPE> list = new ArrayList<>();

        private int        cnt  = 0;

        /**
         * Adds the type.
         *
         * @param type the type
         * @return this builder
         */
        public TypePair.Builder<TYPE> addType(TYPE type)
        {
            if(cnt < 2)
                list.add(type);
            return this;
        }

        /**
         * Builds the.
         *
         * @return the type pair
         */
        public TypePair<TYPE> build()
        {
            return new TypePair<>(this);
        }
    }
}
