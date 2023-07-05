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
