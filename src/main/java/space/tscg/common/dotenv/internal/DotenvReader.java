/**
 * Copyright (C) 2023  The Stellar Cartographers' Guild
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package space.tscg.common.dotenv.internal;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import space.tscg.common.dotenv.DotenvException;

/**
 * (Internal) Reads a .env file
 */
public class DotenvReader
{
    private final String directory;
    private final String filename;

    /**
     * Creates a dotenv reader
     * @param directory the directory containing the .env file
     * @param filename the file name of the .env file e.g. .env
     */
    public DotenvReader(String directory, String filename)
    {
        this.directory = directory;
        this.filename = filename;
    }

    /**
     * (Internal) Reads the .env file
     * @return a list containing the contents of each line in the .env file
     * @throws DotenvException if a dotenv error occurs
     * @throws IOException if an I/O error occurs
     */
    public List<String> read() throws DotenvException, IOException
    {
        var dir                = directory.replaceAll("\\\\", "/").replaceFirst("\\.env$", "").replaceFirst("/$", "");
        var location           = dir + "/" + filename;
        var localLocation      = dir + "/" + "env.local";
        var lowerLocation      = location.toLowerCase();
        var lowerLocalLocation = localLocation.toLowerCase();
        var path               = lowerLocation.startsWith("file:") || lowerLocation.startsWith("android.resource:") ? Paths.get(URI.create(location)) : Paths.get(location);
        var localPath          = lowerLocalLocation.startsWith("file:") || lowerLocalLocation.startsWith("android.resource:") ? Paths.get(URI.create(localLocation)) : Paths.get(localLocation);

        if(Files.exists(localPath))
        {
            return Files.readAllLines(localPath);
        }
        if (Files.exists(path))
        {
            return Files.readAllLines(path);
        }
        try
        {
            return ClasspathHelper.loadFileFromClasspath(location.replaceFirst("^\\./", "/")).collect(Collectors.toList());
        } catch (DotenvException e)
        {
            var cwd        = FileSystems.getDefault().getPath(".").toAbsolutePath().normalize();
            var cwdMessage = !path.isAbsolute() ? "(working directory: " + cwd + ")" : "";
            e.addSuppressed(new DotenvException("Could not find " + path + " on the file system " + cwdMessage));
            throw e;
        }
    }
}
