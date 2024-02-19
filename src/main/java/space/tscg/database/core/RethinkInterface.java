/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.database.core;

import static com.rethinkdb.RethinkDB.*;

import gdn.rom.env.Environment;
import lombok.Getter;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.ast.*;
import com.rethinkdb.gen.exc.ReqlAuthError;
import com.rethinkdb.net.*;

import java.util.concurrent.ScheduledExecutorService;

import space.tscg.database.RethinkLogin;
import space.tscg.misc.ExecutorFactory;

public abstract class RethinkInterface
{
    @Getter
    private final ScheduledExecutorService executor = ExecutorFactory.newScheduledThreadPool(1, "RethinkInterface-Thread", false);
    private RethinkLogin                   login;
    private Connection                     connection;

    protected RethinkInterface()
    {
        this.login = RethinkLogin.Builder().database(Environment.get("database").toString()).build();
        RethinkDB.setResultMapper(DefaultRethinkMapper.getDefault());
    }

    protected RethinkInterface(RethinkLogin login)
    {
        this.login = login;
        RethinkDB.setResultMapper(DefaultRethinkMapper.getDefault());
    }

    /**
     * Gets the credentials.
     *
     * @return the credentials
     */
    protected RethinkLogin getRethinkLogin()
    {
        return this.login;
    }

    /**
     * Connection.
     *
     * @return the connection
     */
    protected Connection connection()
    {
        if (connection == null)
        {
            try
            {
                connection = this.login.connectionBuilder().connect();
            } catch (ReqlAuthError e)
            {
                throw new ReqlAuthError("RethinkDb Error: " + e.getMessage());
            }
        }
        return connection;
    }

    public Table table(String name)
    {
        return r.table(name);
    }

    public <T> Result<T> getAll(String tableName, Class<T> target)
    {
        return r.table(tableName).run(connection(), target);
    }

    public Result<?> runExpr(ReqlExpr expr)
    {
        return expr.run(connection());
    }

    public <T> T runExpr(ReqlExpr expr, Class<T> cls)
    {
        return expr.run(connection(), cls).single();
    }
}
