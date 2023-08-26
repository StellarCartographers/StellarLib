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

import static com.rethinkdb.RethinkDB.r;

import java.util.function.Function;

import com.rethinkdb.gen.ast.Insert;
import com.rethinkdb.gen.exc.ReqlAuthError;
import com.rethinkdb.net.Connection;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import space.tscg.common.db.modal.DbEntity;
import space.tscg.common.db.modal.ManagedObject;
import space.tscg.common.db.op.Operation;
import space.tscg.common.dotenv.Dotenv;

public abstract class Database
{
    private DBCredentials credentials;

    private Connection connection;

    /**
     * Instantiates a new database.
     */
    protected Database()
    {
        this.credentials = buildDefault();
        connection();
    }

    private DBCredentials buildDefault()
    {
        return DBCredentials.builder().databaseName(Dotenv.get("database")).build();
    }

    /**
     * Instantiates a new database.
     *
     * @param credentials the credentials
     */
    protected Database(DBCredentials credentials)
    {
        this.credentials = credentials;
        connection();
    }

    /**
     * Gets the credentials.
     *
     * @return the credentials
     */
    protected DBCredentials getCredentials()
    {
        return this.credentials;
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
                connection = this.credentials.getConnectionBuilder().connect();
            } catch (ReqlAuthError e)
            {
                throw new ReqlAuthError("unknown user: " + this.credentials.getUsername());
            }
        }
        return connection;
    }

    /**
     * Deletes the object from the database.
     * <p>
     * <strong>OPERATION IS DESTRUCTIVE AND CANNOT BE UNDONE AFTER COMPLETED</strong>
     *
     * @param object DbEntity object
     * @return {@link Operation} result
     */
    public Operation delete(DbEntity object)
    {
        return r.table(object.getTableName()).get(object.getId()).delete().run(connection(), Operation.class).single();
    }

    /**
     * Convienance method that helps indicate an object is created and saved to the database.
     * But will conflict if an object with the same id already exists. 
     * <br>You can use <code>getFirstError().startsWith("Duplicate primary key")</code> on the
     * returned {@link Operation} instance to check for a conflict.
     *
     * @param object DbEntity object
     * @return {@link Operation} result
     */
    public Operation create(DbEntity object)
    {
        return InternalOp.CREATE.query(object).run(connection(), Operation.class).single();
    }

    /**
     * Saves the object to the database and returns nothing. 
     * <p>
     * <strong> Note: Save operations completely replace all values for object in the database</strong>
     *
     * @param object DbEntity object
     * @see {@link #save(DbEntity)}
     * @see {@link #update(DbEntity)}
     * @see {@link #updateNoReply(DbEntity)}
     */
    public void saveNoReply(DbEntity object)
    {
        InternalOp.SAVE.query(object).runNoReply(connection());
    }

    /**
     * Saves the object to the database and returns a {@link Operation}. 
     * <p>
     * <strong> Note: Save operations completely replace all values for object in the database</strong>
     *
     * @param object DbEntity object
     * @return {@link Operation} result
     * @see {@link #saveNoReply(DbEntity)}
     * @see {@link #update(DbEntity)}
     * @see {@link #updateNoReply(DbEntity)}
     */
    public Operation save(DbEntity object)
    {
        return InternalOp.SAVE.query(object).run(connection(), Operation.class).single();
    }

    /**
     * Updates the object in the database and returns nothing
     * <p>
     * <strong> Note: Update operations only replace values that have changed for the object in the database</strong>
     * 
     * @param object DbEntity object
     * @see {@link #saveNoReply(DbEntity)}
     * @see {@link #save(DbEntity)}
     * @see {@link #update(DbEntity)}
     */
    public void updateNoReply(DbEntity object)
    {
        InternalOp.UPDATE.query(object).runNoReply(connection());
    }

    /**
     * Updates the object in the database and returns a {@link Operation}. 
     * <p>
     * <strong> Note: Update operations only replace values that have changed for the object in the database</strong>
     *
     * @param object DbEntity object
     * @return {@link Operation} result
     * @see {@link #saveNoReply(DbEntity)}
     * @see {@link #save(DbEntity)}
     * @see {@link #updateNoReply(DbEntity)}
     */
    public Operation update(DbEntity object)
    {
        return InternalOp.UPDATE.query(object).run(connection(), Operation.class).single();
    }

    /**
     * Convienance method that helps indicate an object is created and saved to the database.
     * But will conflict if an object with the same id already exists. 
     * <br>You can use <code>getFirstError().startsWith("Duplicate primary key")</code> on the
     * returned {@link Operation} instance to check for a conflict.
     *
     * @param object DbEntity object
     * @return {@link Operation} result
     * @deprecated migrate your {@link ManagedObject} to {@link DbEntity} then use {@link #create(DbEntity)} instead
     */
    @Deprecated
    public Operation create(ManagedObject object)
    {
        return InternalOp.CREATE.query(object).run(connection(), Operation.class).single();
    }

    /**
     * Saves the object to the database and returns nothing. 
     * <p>
     * <strong> Note: Save operations completely replace all values for object in the database</strong>
     *
     * @param object ManagedObject object
     * @deprecated migrate your {@link ManagedObject} to {@link DbEntity} then use {@link #saveNoReply(DbEntity)} instead
     */
    @Deprecated
    public void saveNoReply(ManagedObject object)
    {
        InternalOp.SAVE.query(object).runNoReply(connection());
    }

    /**
     * Saves the object to the database and returns a {@link Operation}. 
     * <p>
     * <strong> Note: Save operations completely replace all values for object in the database</strong>
     *
     * @param object ManagedObject object
     * @return {@link Operation} result
     * @deprecated migrate your {@link ManagedObject} to {@link DbEntity} then use {@link #save(DbEntity)} instead
     */
    @Deprecated
    public Operation save(ManagedObject object)
    {
        return InternalOp.SAVE.query(object).run(connection(), Operation.class).single();
    }

    /**
     * Updates the object in the database and returns nothing
     * <p>
     * <strong> Note: Update operations only replace values that have changed for the object in the database</strong>
     *
     * @param object ManagedObject object
     * @deprecated migrate your {@link ManagedObject} to {@link DbEntity} then use {@link #updateNoReply(DbEntity)} instead
     */
    @Deprecated
    public void updateNoReply(ManagedObject object)
    {
        InternalOp.UPDATE.query(object).runNoReply(connection());
    }

    /**
     * Updates the object in the database and returns a {@link Operation}. 
     * <p>
     * <strong> Note: Update operations only replace values that have changed for the object in the database</strong>
     *
     * @param object ManagedObject object
     * @return {@link Operation} result
     * @deprecated migrate your {@link ManagedObject} to {@link DbEntity} then use {@link #update(DbEntity)} instead
     */
    @Deprecated
    public Operation update(ManagedObject object)
    {
        return InternalOp.UPDATE.query(object).run(connection(), Operation.class).single();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    private enum InternalOp
    {
        CREATE,
        UPDATE(i -> i.optArg("conflict", "update")),
        SAVE(i -> i.optArg("conflict", "replace"));

        private Function<Insert, Insert> func = i -> i;

        @Deprecated
        Insert query(ManagedObject obj)
        {
            return this.func.apply(r.table(obj.getTableName()).insert(obj));
        }

        Insert query(DbEntity obj)
        {
            return this.func.apply(r.table(obj.getTableName()).insert(obj));
        }
    }
}
