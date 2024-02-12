/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.database;

import gdn.rom.env.Environment;
import lombok.*;
import lombok.Builder;
import lombok.experimental.Accessors;

import com.rethinkdb.net.Connection;

import org.apache.commons.lang3.builder.*;

@Value
@Builder(toBuilder = true, builderClassName = "LoginBuilder", builderMethodName = "Builder")
@Accessors(fluent = true)
public final class RethinkLogin
{
    @lombok.Builder.Default
    private String hostname = Environment.get("rethink_hostname", "localhost").toString();
    @lombok.Builder.Default
    private String username = Environment.get("rethink_username", "admin").toString();
    @lombok.Builder.Default
    private String password = Environment.get("rethink_password", null).toString();
    @lombok.Builder.Default
    private int    port     = Environment.get("rethink_port", 28015).toInt();
    private String database;

    public Connection.Builder connectionBuilder()
    {
        var builder = new Connection.Builder().hostname(this.hostname).port(this.port);
        return this.password != null ? builder.user(username, password) : builder.user(username);
    }
    
    public String getLoginCredentials()
    {
        return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                        .append("database", this.database)
                        .append("username", this.username)
                        .append("password", this.password)
                        .build();
    }
    
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
            .append("hostname", this.hostname)
            .append("database", this.database)
            .append("port", this.port)
            .append("username", this.username)
            .append("password", this.password)
            .build();
    }
}
