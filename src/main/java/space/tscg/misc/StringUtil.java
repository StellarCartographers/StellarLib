/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.misc;

import lombok.experimental.UtilityClass;

import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

import space.tscg.functions.*;

@UtilityClass
public class StringUtil
{
    private static ThreadLocalRandom random() {
        return ThreadLocalRandom.current();
    }
    
    public static FunctionSupplier<String> shuffle()
    {
        return t -> {
            // Convert the string to a character array.
            char[] chars = t.toCharArray();
            // Shuffle the character array using the Fisher-Yates shuffle algorithm.
            for (int i = chars.length - 1; i > 0; i--) {
                int j = (int) (Math.random() * (i + 1));
                // Swap the characters at indices i and j.
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
            }
            // Convert the character array back to a string and return it.
            return new String(chars);
        };
    }
    
    public static String capitalize(String s)
    {
        if (s.length() == 0)
        {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static String uncapitalize(String s)
    {
        if (s.length() == 0)
        {
            return s;
        }
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    @SafeVarargs
    public static String randomString(int length, boolean toUpperCase, StringSupplier<String>... runAgainstValue)
    {
        char[] chars = null;
        int end = 'z' + 1;
        int start = ' ';
        
        final StringBuilder builder = new StringBuilder(length);
        final int gap = end - start;
        while (length-- != 0)
        {
            final int codePoint;
            if (chars == null)
            {
                codePoint = ThreadLocalRandom.current().nextInt(gap) + start;
                switch (Character.getType(codePoint)) {
                    case Character.UNASSIGNED:
                    case Character.PRIVATE_USE:
                    case Character.SURROGATE:
                        length++;
                        continue;
                }
            } else
            {
                codePoint = chars[ThreadLocalRandom.current().nextInt(gap) + start];
            }
            final int numberOfChars = Character.charCount(codePoint);
            if (length == 0 && numberOfChars > 1)
            {
                length++;
                continue;
            }
            if (Character.isLetter(codePoint) || Character.isDigit(codePoint))
            {
                builder.appendCodePoint(codePoint);
                if (numberOfChars == 2)
                {
                    length--;
                }
            } else
            {
                length++;
            }
        }
        String out = builder.toString();
        if(toUpperCase)
            out = out.toUpperCase();
        for(var consumer : runAgainstValue)
        {
            out = consumer.apply(out);
        }
        return out;
    }
    
    public static String randomNumberString(int length)
    {
        return randomNumberString(length, null);
    }
    
    public static String randomNumberString(int length, @Nullable PredicatedMonoFunction<String> predicated)
    {
        var idArray = new String[10];
        int zeros = 0;
        var previous = 0;
        for (int i = 0; i < idArray.length; i++)
        {
            int nextNumber;
            nextNumber = nextIndex(zeros);
            if(nextNumber == 0)
                zeros++;
            while(nextNumber == previous)
            {
                nextNumber = nextIndex(zeros);
            }
            idArray[i] = String.valueOf(nextNumber);
            previous = nextNumber;
        }
        
        String out = String.join("", idArray);
        if(predicated != null) {
            while(predicated.test(out)) {
                out = predicated.function(out);
            }
        }
        return out;
    }
    
    private static int nextIndex(int zeroCount)
    {
        return (zeroCount < 2) ? random().nextInt(0, 9) : random().nextInt(1, 9);
    }
    
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    static String encodedHexString(final String string)
    {
        return bytesToHex(string.getBytes());
    }
    
    static String bytesToHex(byte[] bytes)
    {
        var HEX_ARRAY = new String(DIGITS_LOWER).getBytes(StandardCharsets.US_ASCII);
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++)
        {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8).toLowerCase();
    }
}
