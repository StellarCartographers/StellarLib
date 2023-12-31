package space.tscg.misc;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil
{
    public static String capitalize(String s)
    {
        if (s.length() == 0) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static String uncapitalize(String s)
    {
        if (s.length() == 0) {
            return s;
        }
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }
}
