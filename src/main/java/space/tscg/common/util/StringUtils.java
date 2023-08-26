package space.tscg.common.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils
{
    /**
     * Returns {@code true} if the specified char sequence is all blank,
     * empty or {@code null}.
     *
     * @param cs The char sequence. May be {@code null}.
     *
     * @return {@code true} if the specified char sequence is all blank,
     *         empty or {@code null}, else {@code false}.
     */
    public static boolean isBlank(final CharSequence cs)
    {
        int strLen;
        if ((cs != null) && ((strLen = cs.length()) != 0))
        {
            for (int i = 0; i < strLen; ++i)
            {
                if (!Character.isWhitespace(cs.charAt(i)))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns {@code true} if the specified char sequence is not all
     * blank, not empty and not {@code null}.
     *
     * @param cs The char sequence. May be {@code null}.
     *
     * @return {@code true} if the specified char sequence is not all
     *         blank, not empty and not {@code null}, else {@code false}.
     */
    public static boolean isNotBlank(final CharSequence cs)
    {
        return !isBlank(cs);
    }

    /**
     * Returns {@code true} if the specified char sequence is all
     * alphabetic letters.
     *
     * @param cs The char sequence. May be {@code null}.
     *
     * @return {@code true} if the specified char sequence is all
     *         alphabetic letters, empty or {@code null}, else
     *         {@code false}.
     */
    public static boolean isAlpha(final CharSequence cs)
    {
        int strLen;
        if ((cs != null) && ((strLen = cs.length()) != 0))
        {
            for (int i = 0; i < strLen; ++i)
            {
                if (!Character.isAlphabetic(cs.charAt(i)))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns {@code true} if the specified char sequence is all numeric
     * letters.
     *
     * @param cs The char sequence. May be {@code null}.
     *
     * @return {@code true} if the specified char sequence is all numeric
     *         letters, empty or {@code null}, else {@code false}.
     */
    public static boolean isNumeric(final CharSequence cs)
    {
        int strLen;
        if ((cs != null) && ((strLen = cs.length()) != 0))
        {
            for (int i = 0; i < strLen; ++i)
            {
                if (!Character.isDigit(cs.charAt(i)))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
