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
package space.tscg.common.db;

import com.rethinkdb.net.Connection;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import space.tscg.common.dotenv.Dotenv;

@Value
@Builder(toBuilder = true, builderClassName = "Builder")
public final class DBCredentials
{
    @NonFinal @Setter
    private String databaseName;

    @lombok.Builder.Default
    private String hostname = Dotenv.get("db_host");
    @lombok.Builder.Default
    private String username = Dotenv.get("db_user");
    @lombok.Builder.Default
    private String password = Dotenv.get("db_password");

    @lombok.Builder.Default
    private int port = Dotenv.getInt("db_port", 28015);

    public Connection.Builder getConnectionBuilder()
    {
        return new Connection.Builder().hostname(this.hostname).port(this.port).db(this.databaseName).user(this.username, this.password);
    }
}
