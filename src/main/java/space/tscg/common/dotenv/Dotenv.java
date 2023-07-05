/**
 * dotenv-java
 * copyright 2021 - Carmine DiMascio
 * license - Apache 2.0
 */
package space.tscg.common.dotenv;

import java.util.Set;

/**
 * Creates and configures a new Dotenv instance
 */
public interface Dotenv
{

    /**
     * A dotenv entry filter
     */
    enum Filter
    {
        /**
         * Filter matching only environment variables declared in the .env file
         */
        DECLARED_IN_ENV_FILE
    }

    /**
     * Configures a new {@link Dotenv} instance
     * @return a new {@link Dotenv} instance
     */
    static DotenvBuilder configure()
    {
        return new DotenvBuilder();
    }

    /**
     * Creates and loads a {@link Dotenv} instance with default options
     * @return a new {@link Dotenv} instance
     */
    static Dotenv load()
    {
        return new DotenvBuilder().defaultConfiguration();
    }

    /**
     * Returns the set of environment variables with values
     * @return the set of {@link DotenvEntry}s for all environment variables
     */
    Set<DotenvEntry> entries();

    /**
     * Returns the set of {@link DotenvEntry}s matching the filter
     * @param filter the filter e.g. {@link Dotenv.Filter}
     * @return the set of {@link DotenvEntry}s for environment variables matching the {@link Dotenv.Filter}
     */
    Set<DotenvEntry> entries(Filter filter);

    /**
     * Retrieves the int value of the environment variable specified by key.
     * 
     * <br>returns {@link Integer.MIN_VALUE} if value cannot be parsed as int
     * @param key the environment variable
     * @return the int value of the environment variable, or {@link Integer.MIN_VALUE}
     */
    int getAsInt(String key);

    /**
     * Retrieves the int value of the environment variable specified by key.
     * If the key does not exist, then the default value is returned
     * @param key the environment variable
     * @param defaultValue the default int value to return
     * @return the int value of the environment variable or default value
     */
    int getAsInt(String key, int defaultValue);

    /**
     * Retrieves the value of the environment variable specified by key
     * @param key the environment variable
     * @return the value of the environment variable
     */
    String get(String key);

    /**
     * Retrieves the value of the environment variable specified by key.
     * If the key does not exist, then the default value is returned
     * @param key the environment variable
     * @param defaultValue the default value to return
     * @return the value of the environment variable or default value
     */
    String get(String key, String defaultValue);
}
