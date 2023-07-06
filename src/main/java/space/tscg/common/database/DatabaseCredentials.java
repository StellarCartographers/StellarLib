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
package space.tscg.common.database;

import static com.rethinkdb.RethinkDB.r;

import com.rethinkdb.net.Connection;

import lombok.Builder;
import lombok.NonNull;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@Builder(toBuilder = true, builderClassName = "Builder")
public final class DatabaseCredentials
{

    @NonFinal
    @Setter
    private String databaseName;
    @NonNull
    private String hostname, username, password;
    @lombok.Builder.Default
    private int    port = 28015;

    public Connection getConnection(String databaseName)
    {
        return r
            .connection()
            .hostname(this.hostname)
            .port(this.port)
            .db(databaseName)
            .user(this.username, this.password)
            .connect();
    }

    public Connection getConnection()
    {
        return r
            .connection()
            .hostname(this.hostname)
            .port(this.port)
            .db(this.databaseName)
            .user(this.username, this.password)
            .connect();
    }
}
