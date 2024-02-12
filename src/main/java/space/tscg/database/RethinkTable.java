package space.tscg.database;

import panda.std.Blank;

import com.rethinkdb.gen.ast.*;
import com.rethinkdb.net.Result;

import java.util.List;
import java.util.stream.Collectors;

import space.tscg.api.database.DbEntity;
import space.tscg.database.defined.TSCGDatabase;
import space.tscg.database.operation.*;

public abstract class RethinkTable<T extends DbEntity>
{
    protected TSCGDatabase database = TSCGDatabase.instance();
    private Table table;
    
    protected RethinkTable(String name)
    {
        this.table = database.table(name);
    }
    
    public abstract Class<T> getTypeClass();
    
    public List<T> allEntries()
    {
        return run(table).collect(Collectors.toList());
    }
    
    public int entryCount()
    {
        return run(table.count(), Integer.class);
    }

    public panda.std.Result<T, Blank> get(String id)
    {
        var t = table.get(id).runAtom(database.getConn(), getTypeClass());
        return panda.std.Result.when(t != null, t, Blank.BLANK);
                        
    }

    public panda.std.Result<T, Blank> get(T entity)
    {
        return get(entity.getId());
    }

    public InsertOperation add(T entity)
    {
        return run(table.insert(entity), InsertOperation.class);
    }

    public UpdateOperation update(T entity)
    {
        return run(table.insert(entity).optArg("conflict", "update"), UpdateOperation.class);
    }

    public DeleteOperation delete(T entity)
    {
        return delete(entity.getId());
    }
    
    public DeleteOperation delete(String id)
    {
        return run(table.get(id).delete(), DeleteOperation.class);
    }

    public ReplaceOperation replace(T entity)
    {
        return run(table.insert(entity).optArg("conflict", "replace"), ReplaceOperation.class);
    }
    
    private <TYPE> TYPE run(ReqlExpr expr, Class<TYPE> type)
    {
        return expr.runAtom(database.getConn(), type);
    }
    
    private Result<T> run(ReqlExpr expr)
    {
        return expr.run(database.getConn(), getTypeClass());
    }
}
