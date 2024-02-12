package space.tscg.database.entity;

import space.tscg.database.RethinkTable;

public class MemberTable extends RethinkTable<TSCGMember>
{
    private volatile static MemberTable instance;

    public static MemberTable instance()
    {
        if (instance == null)
        {
            synchronized (MemberTable.class)
            {
                if (instance == null)
                {
                    instance = new MemberTable();
                }
            }
        }
        return instance;
    }
    
    protected MemberTable()
    {
        super("member");
    }

    @Override
    public Class<TSCGMember> getTypeClass()
    {
        return TSCGMember.class;
    }
}
