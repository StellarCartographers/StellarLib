package space.tscg.common.db.prefab;

import static com.rethinkdb.RethinkDB.r;

import com.rethinkdb.gen.ast.ReqlExpr;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Result;

import space.tscg.common.db.Database;

public class TSCGDatabase extends Database
{
    private static final TSCGDatabase _instance = new TSCGDatabase();

    public static TSCGDatabase instance()
    {
        return _instance;
    }

    public static Connection getConn()
    {
        return instance().connection();
    }

    public <T> Result<T> getAll(String tableName, Class<T> target)
    {
        return r.table(tableName).run(connection(), target);
    }
    
    public Result<?> runExpr(ReqlExpr expr)
    {
        return expr.run(getConn());
    }
    
    public <T> T runExpr(ReqlExpr expr, Class<T> cls)
    {
        return expr.run(getConn(), cls).single();
    }
}
