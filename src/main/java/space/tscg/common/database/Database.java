package space.tscg.common.database;

import static com.rethinkdb.RethinkDB.r;

import java.util.function.Function;

import com.rethinkdb.gen.ast.Insert;
import com.rethinkdb.net.Connection;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public abstract class Database
{
    private DatabaseCredentials credentials;
    private static Connection connection;

    /**
     * Instantiates a new database.
     *
     * @param credentials the credentials
     */
    protected Database(DatabaseCredentials credentials)
    {
        this.credentials = credentials;
    }

    /**
     * Connection.
     *
     * @return the connection
     */
    protected Connection connection()
    {
        if(connection == null)
        {
            connection = this.credentials.getConnection();
        }

        return connection;
    }

    /**
     * Delete.
     *
     * @param object the object
     * @return the operation
     */
    public Operation delete(ManagedObject object)
    {
        return r.table(object.getTableName()).get(object.getId()).delete().run(connection, Operation.class).single();
    }

    /**
     * Creates the.
     *
     * @param object the object
     * @return the operation
     */
    public Operation create(ManagedObject object)
    {
        return InternalOp.CREATE.query(object).run(connection, Operation.class).single();
    }

    /**
     * Save no reply.
     *
     * @param object the object
     */
    public void saveNoReply(ManagedObject object)
    {
        InternalOp.SAVE.query(object).runNoReply(connection);
    }

    /**
     * Save.
     *
     * @param object the object
     * @return the operation
     */
    public Operation save(ManagedObject object)
    {
        return InternalOp.SAVE.query(object).run(connection, Operation.class).single();
    }

    /**
     * Update no reply.
     *
     * @param object the object
     */
    public void updateNoReply(ManagedObject object)
    {
        InternalOp.UPDATE.query(object).runNoReply(connection);
    }

    /**
     * Update.
     *
     * @param object the object
     * @return the operation
     */
    public Operation update(ManagedObject object)
    {
        return InternalOp.UPDATE.query(object).run(connection, Operation.class).single();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    private enum InternalOp  {
        CREATE,
        UPDATE(i -> i.optArg("conflict", "update")),
        SAVE(i -> i.optArg("conflict", "replace"));

        private Function<Insert, Insert> func = i -> i;

        Insert query(ManagedObject obj)
        {
            return this.func.apply(r.table(obj.getTableName()).insert(obj));
        }
    }
}
