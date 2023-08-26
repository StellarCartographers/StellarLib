package space.tscg.common.db.prefab;

import static com.rethinkdb.RethinkDB.r;

import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Result;

import space.tscg.common.db.Database;

public class TSCGDatabase extends Database
{
    private static final TSCGDatabase _instance = new TSCGDatabase();

    public static TSCGDatabase get()
    {
        return _instance;
    }

    static Connection getConn()
    {
        return get().connection();
    }

    public <T> Result<T> getAll(String tableName, Class<T> target)
    {
        return r.table(tableName).run(connection(), target);
    }
}
