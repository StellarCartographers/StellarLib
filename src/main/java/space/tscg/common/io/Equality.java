package space.tscg.common.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

import lombok.experimental.UtilityClass;

/**
 * Didn't feel like writting a javadoc for this one. Deal with it
 */
@UtilityClass
public class Equality
{
    /**
     * CR char.
     */
    public static final int CR = '\r';

    /**
     * The default buffer size ({@value}) to use in copy methods.
     */
    public static final int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * The system directory separator character.
     */
    public static final char DIR_SEPARATOR = File.separatorChar;

    /**
     * The Unix directory separator character.
     */
    public static final char DIR_SEPARATOR_UNIX = '/';

    /**
     * The Windows directory separator character.
     */
    public static final char DIR_SEPARATOR_WINDOWS = '\\';

    /**
     * A singleton empty byte array.
     */
    public static final byte[] EMPTY_BYTE_ARRAY = {};

    /**
     * Represents the end-of-file (or stream).
     */
    public static final int EOF = -1;

    /**
     * LF char.
     */
    public static final int LF = '\n';

    /**
     * Internal byte array buffer, intended for both reading and writing.
     */
    private static final ThreadLocal<byte[]> SCRATCH_BYTE_BUFFER_RW = ThreadLocal.withInitial(Equality::byteArray);

    /**
     * Internal byte array buffer, intended for write only operations.
     */
    private static final byte[] SCRATCH_BYTE_BUFFER_WO = byteArray();

    /**
     * Returns a new byte array of the given size.
     *
     * TODO Consider guarding or warning against large allocations...
     *
     * @param size array size.
     * @return a new byte array of the given size.
     * @throws NegativeArraySizeException if the size is negative.
     */
    public static byte[] byteArray(final int size)
    {
        return new byte[size];
    }

    /**
     * Returns a new byte array of size {@link #DEFAULT_BUFFER_SIZE}.
     *
     * @return a new byte array of size {@link #DEFAULT_BUFFER_SIZE}.
     */
    public static byte[] byteArray()
    {
        return byteArray(DEFAULT_BUFFER_SIZE);
    }

    /**
     * Returns the given reader if it is a {@link BufferedReader}, otherwise creates a BufferedReader from the given
     * reader.
     *
     * @param reader the reader to wrap or return (not null)
     * @return the given reader or a new {@link BufferedReader} for the given reader
     */
    public static BufferedReader toBufferedReader(final Reader reader)
    {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    /**
     * Returns the given reader if it is a {@link BufferedReader}, otherwise creates a BufferedReader from the given
     * reader.
     *
     * @param reader the reader to wrap or return (not null)
     * @param size the buffer size, if a new BufferedReader is created.
     * @return the given reader or a new {@link BufferedReader} for the given reader
     */
    public static BufferedReader toBufferedReader(final Reader reader, final int size)
    {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, size);
    }

    /**
     * 
     */
    public static boolean contentEquals(final Stream<?> stream1, final Stream<?> stream2)
    {
        if (stream1 == stream2)
        {
            return true;
        }
        if ((stream1 == null) || (stream2 == null))
        {
            return false;
        }
        return contentEquals(stream1.iterator(), stream2.iterator());
    }

    /**
     * 
     */
    public static boolean contentEqualsIgnoreEOL(final BufferedReader reader1, final BufferedReader reader2)
    {
        if (reader1 == reader2)
        {
            return true;
        }
        if ((reader1 == null) || (reader2 == null))
        {
            return false;
        }
        return contentEquals(reader1.lines(), reader2.lines());
    }

    /**
     * Compares the contents of two Readers to determine if they are equal or
     * not, ignoring EOL characters.
     * <p>
     * This method buffers the input internally using
     * {@link BufferedReader} if they are not already buffered.
     * </p>
     *
     * @param reader1 the first reader
     * @param reader2 the second reader
     * @return true if the content of the readers are equal (ignoring EOL differences),  false otherwise
     */
    public static boolean contentEqualsIgnoreEOL(final Reader reader1, final Reader reader2)
    {
        if (reader1 == reader2)
        {
            return true;
        }
        if ((reader1 == null) || (reader2 == null))
        {
            return false;
        }
        return contentEqualsIgnoreEOL(toBufferedReader(reader1), toBufferedReader(reader2));
    }

    /**
     * 
     */
    public static boolean contentEquals(final Iterator<?> iterator1, final Iterator<?> iterator2)
    {
        while (iterator1.hasNext())
        {
            if (!iterator2.hasNext() || !Objects.equals(iterator1.next(), iterator2.next()))
            {
                return false;
            }
        }
        return !iterator2.hasNext();
    }

    /**
     * Compares the contents of two Streams to determine if they are equal or
     * not.
     * <p>
     * This method buffers the input internally using
     * {@link BufferedInputStream} if they are not already buffered.
     * </p>
     *
     * @param input1 the first stream
     * @param input2 the second stream
     * @return true if the content of the streams are equal or they both don't
     * exist, false otherwise
     */
    public static boolean contentEquals(final InputStream input1, final InputStream input2)
    {
        // Before making any changes, please test with
        // org.apache.commons.io.jmh.IOUtilsContentEqualsInputStreamsBenchmark
        if (input1 == input2)
        {
            return true;
        }
        if ((input1 == null) || (input2 == null))
        {
            return false;
        }
        // reuse one
        final var array1 = getScratchByteArray();
        // allocate another
        final var array2 = byteArray();
        int       pos1;
        int       pos2;
        int       count1;
        int       count2;
        while (true)
        {
            pos1 = 0;
            pos2 = 0;
            for (var index = 0; index < DEFAULT_BUFFER_SIZE; index++)
            {
                if (pos1 == index)
                {
                    do
                    {
                        count1 = readStream(input1, array1, pos1, DEFAULT_BUFFER_SIZE - pos1);
                    } while (count1 == 0);
                    if (count1 == EOF)
                    {
                        return (pos2 == index) && (readStream(input2) == EOF);
                    }
                    pos1 += count1;
                }
                if (pos2 == index)
                {
                    do
                    {
                        count2 = readStream(input2, array2, pos2, DEFAULT_BUFFER_SIZE - pos2);
                    } while (count2 == 0);
                    if (count2 == EOF)
                    {
                        return (pos1 == index) && (readStream(input1) == EOF);
                    }
                    pos2 += count2;
                }
                if (array1[index] != array2[index])
                {
                    return false;
                }
            }
        }
    }

    private int readStream(InputStream stream)
    {
        try
        {
            return stream.read();
        } catch (IOException e)
        {
            return -666;
        }
    }

    private int readStream(InputStream stream, byte b[], int off, int len)
    {
        try
        {
            return stream.read(b, off, len);
        } catch (IOException e)
        {
            return -666;
        }
    }

    /**
     * Gets the internal byte array buffer, intended for both reading and writing.
     *
     * @return the internal byte array buffer, intended for both reading and writing.
     */
    static byte[] getScratchByteArray()
    {
        return fill0(SCRATCH_BYTE_BUFFER_RW.get());
    }

    /**
     * Gets the internal byte array intended for write only operations.
     *
     * @return the internal byte array intended for write only operations.
     */
    static byte[] getScratchByteArrayWriteOnly()
    {
        return fill0(SCRATCH_BYTE_BUFFER_WO);
    }

    /**
     * Fills the given array with 0s.
     *
     * @param arr The array to fill.
     * @return The given array.
     */
    private static byte[] fill0(final byte[] arr)
    {
        Arrays.fill(arr, (byte) 0);
        return arr;
    }
}
