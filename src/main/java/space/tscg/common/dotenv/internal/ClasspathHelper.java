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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import space.tscg.common.dotenv.DotenvException;

/**
 * Classpath helper
 */
class ClasspathHelper
{
    static Stream<String> loadFileFromClasspath(String location) {
        final var loader = ClasspathHelper.class;
        var inputStream = loader.getResourceAsStream(location);
        if (inputStream == null) {
            inputStream = loader.getResourceAsStream(location);
        }
        if (inputStream == null) {
            inputStream = ClassLoader.getSystemResourceAsStream(location);
        }

        if (inputStream == null) {
            throw new DotenvException("Could not find "+location+" on the classpath");
        }

        try (var scanner = new Scanner(inputStream, StandardCharsets.UTF_8))
        {
            final var lines = new ArrayList<String>();
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }

            return lines.stream();
        }
    }
}
