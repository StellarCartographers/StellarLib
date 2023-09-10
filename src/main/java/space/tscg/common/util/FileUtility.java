package space.tscg.common.util;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import lombok.experimental.UtilityClass;

/**
 * Utility class that only contains a single method at this time
 */
@UtilityClass
public class FileUtility
{
    /**
     * Compares the contents of two files to determine if they are equal or not.
     * <p>
     * This method checks to see if the two files are different lengths or if they
     * point to the same file, before resorting to byte-by-byte comparison of the
     * contents.
     * <p>
     * Code origin: Avalon
     *
     * @param file1 the first file
     * @param file2 the second file
     * @return true if the content of the files are equal or they both don't exist,
     *         false otherwise
     * @throws IOException in case of an I/O error
     */
    public boolean contentEquals(final File file1, final File file2)
    {
        final var file1Exists = file1.exists();
        final var file2Exists = file2.exists();
        if (file1Exists != file2Exists)
        {
            return false;
        }
        if (!file1Exists)
        {
            // two not existing files are equal
            return true;
        }
        if (file1.isDirectory() || file2.isDirectory())
        {
            // don't want to compare directory contents
            throw new RuntimeException("Can't compare directories, only files");
        }
        if (file1.length() != file2.length())
        {
            // lengths differ, cannot be equal
            return false;
        }
        try
        {
            if (file1.getCanonicalFile().equals(file2.getCanonicalFile()))
            {
                // same file
                return true;
            }
        } catch (IOException e)
        { // do nothing if it fails
        }
        try (var input1 = SafeFileInputStream.from(file1); var input2 = SafeFileInputStream.from(file2))
        {
            return Equality.contentEquals(input1, input2);
        }
    }

    private static class SafeFileInputStream extends FileInputStream
    {
        static SafeFileInputStream from(File file)
        {
            try
            {
                return new SafeFileInputStream(file);
            } catch (FileNotFoundException e)
            {
                return new SafeFileInputStream();
            }
        }

        private SafeFileInputStream()
        {
            super(new FileDescriptor());
        }

        private SafeFileInputStream(File file) throws FileNotFoundException
        {
            super(file);
        }

        @Override
        public void close()
        {
            try
            {
                super.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
