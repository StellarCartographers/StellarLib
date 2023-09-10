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
package space.tscg.common.dotenv;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import space.tscg.common.dotenv.internal.DotenvParser;
import space.tscg.common.dotenv.internal.DotenvReader;

/**
 * Builds and loads and {@link Dotenv} instance.
 * @see Dotenv#configure()
 */
public class DotenvBuilder
{
    private String filename = ".env";

    private String directoryPath = "./";

    private boolean systemProperties = true;

    private boolean throwIfMissing = true;

    private boolean throwIfMalformed = true;

    public Dotenv defaultConfiguration()
    {
        return new DotenvBuilder().load();
    }

    /**
     * Sets the directory containing the .env file.
     * @param path the directory containing the .env file
     * @return this {@link DotenvBuilder}
     */
    public DotenvBuilder directory(final String path)
    {
        this.directoryPath = path;
        return this;
    }

    /**
     * Sets the name of the .env file. The default is .env.
     * @param name the filename
     * @return this {@link DotenvBuilder}
     */
    public DotenvBuilder filename(final String name)
    {
        filename = name;
        return this;
    }

    /**
     * Does not throw an exception when .env is missing.
     * @return this {@link DotenvBuilder}
     */
    public DotenvBuilder ignoreIfMissing()
    {
        throwIfMissing = false;
        return this;
    }

    /**
     * Does not throw an exception when .env is malformed.
     * @return this {@link DotenvBuilder}
     */
    public DotenvBuilder ignoreIfMalformed()
    {
        throwIfMalformed = false;
        return this;
    }

    /**
     * Sets each environment variable as system properties.
     * @return this {@link DotenvBuilder}
     */
    public DotenvBuilder systemProperties()
    {
        systemProperties = true;
        return this;
    }

    /**
     * Load the contents of .env into the virtual environment.
     * @return a new {@link Dotenv} instance
     * @throws DotenvException when an error occurs
     */
    public Dotenv load() throws DotenvException
    {
        final var reader = new DotenvParser(new DotenvReader(directoryPath, filename), throwIfMissing, throwIfMalformed);
        final var env    = reader.parse();
        if (systemProperties)
        {
            env.forEach(it -> System.setProperty(it.getKey(), it.getValue()));
        }
        return new DotenvImpl(env);
    }

    static class DotenvImpl implements Dotenv
    {
        private final Map<String, String> envVars;

        private final Set<DotenvEntry> set;

        private final Set<DotenvEntry> setInFile;

        public DotenvImpl(final List<DotenvEntry> envVars)
        {
            final Map<String, String> envVarsInFile = envVars.stream().collect(toMap(DotenvEntry::getKey, DotenvEntry::getValue));
            this.envVars = new HashMap<>(envVarsInFile);
            this.set = this.envVars.entrySet().stream().map(it -> new DotenvEntry(it.getKey(), it.getValue())).collect(collectingAndThen(toSet(), Collections::unmodifiableSet));
            this.setInFile = envVarsInFile.entrySet().stream().map(it -> new DotenvEntry(it.getKey(), it.getValue())).collect(collectingAndThen(toSet(), Collections::unmodifiableSet));
        }

        @Override
        public Set<DotenvEntry> entries()
        {
            return set;
        }

        @Override
        public Set<DotenvEntry> entries(final Dotenv.Filter filter)
        {
            return filter == null ? entries() : setInFile;
        }

        @Override
        public int retrieveAsInt(String key, int defaultValue)
        {
            final var value = envVars.get(key);
            return value == null ? defaultValue : safeInt(value, defaultValue);
        }

        @Override
        public boolean retrieveAsBoolean(String key)
        {
            final var value = retrieve(key);
            return Boolean.valueOf(value);
        }

        @Override
        public boolean retrieveAsBoolean(String key, boolean defaultValue)
        {
            final var value = retrieve(key);
            return value == null ? defaultValue : Boolean.valueOf(value);
        }

        @Override
        public String retrieve(final String key)
        {
            final var value = System.getenv(key);
            return value == null ? envVars.get(key) : value;
        }

        @Override
        public String retrieve(String key, String defaultValue)
        {
            final var value = this.retrieve(key);
            return value == null ? defaultValue : value;
        }

        private Integer safeInt(String value, int fallback)
        {
            try
            {
                return Integer.parseInt(value);
            } catch (NumberFormatException e)
            {
                return fallback;
            }
        }
    }
}
