package space.tscg.database.entity;

import net.dv8tion.jda.api.entities.UserSnowflake;
import panda.std.*;

import space.tscg.api.carrier.IFleetCarrier;
import space.tscg.database.RethinkTable;

public class FleetCarrierTable extends RethinkTable<IFleetCarrier>
{
    private volatile static FleetCarrierTable instance;

    public static FleetCarrierTable instance()
    {
        if (instance == null)
        {
            synchronized (FleetCarrierTable.class)
            {
                if (instance == null)
                {
                    instance = new FleetCarrierTable();
                }
            }
        }
        return instance;
    }

    protected FleetCarrierTable()
    {
        super("carriers");
    }

    @Override
    public Class<IFleetCarrier> getTypeClass()
    {
        return IFleetCarrier.class;
    }

    public Result<IFleetCarrier, Blank> fromMember(TSCGMember member)
    {
        return get(member.elite().carrierId());
    }

    public Result<IFleetCarrier, Blank> fromUserSnowflake(UserSnowflake snowflake)
    {
        var m = TSCGMember.fromUserSnowflake(snowflake);
        return m.isOk() ? fromMember(m.get()) : Result.error();
    }
}
