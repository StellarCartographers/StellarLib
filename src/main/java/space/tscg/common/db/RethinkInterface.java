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

import static com.rethinkdb.RethinkDB.*;

import com.rethinkdb.gen.exc.ReqlAuthError;
import com.rethinkdb.net.Connection;

import space.tscg.api.database.DbEntity;
import space.tscg.common.db.op.DeleteOperation;
import space.tscg.common.db.op.InsertOperation;
import space.tscg.common.db.op.ReplaceOperation;
import space.tscg.common.db.op.UpdateOperation;
import space.tscg.common.db.prefab.Tables;
import space.tscg.common.dotenv.Dotenv;

public abstract class RethinkInterface
{
    private DBCredentials credentials;
    private Connection    connection;

    protected RethinkInterface()
    {
        this.credentials = buildDefault();
    }

    private DBCredentials buildDefault()
    {
        return DBCredentials.builder().databaseName(Dotenv.get("database")).build();
    }

    protected RethinkInterface(DBCredentials credentials)
    {
        this.credentials = credentials;
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
                throw new ReqlAuthError("RethinkDb Error: " + e.getMessage());
            }
        }
        return connection;
    }

    public <T> T get(Tables table, String id, Class<T> type)
    {
        return get(table.toString(), id, type);
    }
    
    public <T> T get(String table, String id, Class<T> type)
    {
        return r.table(table).get(id).runAtom(connection(), type);
    }

    /**
     * Deletes the object from the database.
     * <p>
     * <strong>OPERATION IS DESTRUCTIVE AND CANNOT BE UNDONE AFTER COMPLETED</strong>
     *
     * @param object DbEntity object
     * @return {@link DeleteOperation} result
     */
    public DeleteOperation delete(DbEntity object)
    {
        return r.table(object.getTable().toString()).get(object.getId()).delete().runAtom(connection(), DeleteOperation.class);
    }

    /**
     * Deletes the object from the database.
     * <p>
     * <strong>OPERATION IS DESTRUCTIVE AND CANNOT BE UNDONE AFTER COMPLETED</strong>
     *
     * @param table table name
     * @param id EntityDb id
     * @return {@link DeleteOperation} result
     */
    public DeleteOperation delete(String table, String id)
    {
        return r.table(table).get(id).delete().runAtom(connection(), DeleteOperation.class);
    }

    /**
     * Convienance method that helps indicate an object is created and saved to the database.
     * But will conflict if an object with the same id already exists. 
     * <br>You can use <code>getFirstError().startsWith("Duplicate primary key")</code> on the
     * returned {@link InsertOperation} instance to check for a conflict.
     *
     * @param object DbEntity object
     * @return {@link InsertOperation} result
     */
    public InsertOperation create(DbEntity object)
    {
        return create(object, false);
    }
    
    public InsertOperation create(DbEntity object, boolean returnChanges)
    {
        var insert = r.table(object.getTable().toString()).insert(object);
        return (returnChanges) 
            ? insert.optArg("return_changes", true).runAtom(connection(), InsertOperation.class) 
            : insert.runAtom(connection(), InsertOperation.class);
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
        r.table(object.getTable().toString()).insert(object).optArg("conflict", "replace").runNoReply(connection());
    }

    /**
     * Saves the object to the database and returns a {@link ReplaceOperation}. 
     * <p>
     * <strong> Note: Save operations completely replace all values for object in the database</strong>
     *
     * @param object DbEntity object
     * @return {@link ReplaceOperation} result
     * @see {@link #saveNoReply(DbEntity)}
     * @see {@link #update(DbEntity)}
     * @see {@link #updateNoReply(DbEntity)}
     */
    public ReplaceOperation save(DbEntity object)
    {
        return r.table(object.getTable().toString()).insert(object).optArg("conflict", "replace").runAtom(connection(), ReplaceOperation.class);
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
        r.table(object.getTable().toString()).insert(object).optArg("conflict", "update").runNoReply(connection());
    }

    /**
     * Updates the object in the database and returns a {@link UpdateOperation}. 
     * <p>
     * <strong> Note: Update operations only replace values that have changed for the object in the database</strong>
     *
     * @param object DbEntity object
     * @return {@link UpdateOperation} result
     * @see {@link #saveNoReply(DbEntity)}
     * @see {@link #save(DbEntity)}
     * @see {@link #updateNoReply(DbEntity)}
     */
    public UpdateOperation update(DbEntity object)
    {
        return r.table(object.getTable().toString()).insert(object).optArg("conflict", "update").runAtom(connection(), UpdateOperation.class);
    }
}
