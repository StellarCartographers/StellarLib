package space.tscg.database.entity;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;
import net.dv8tion.jda.api.entities.UserSnowflake;
import space.tscg.api.database.DbEntity;
import space.tscg.database.DefinedTable;
import space.tscg.database.defined.TSCGDatabase;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "Builder", toBuilder = true)
@Jacksonized
public class TSCGMember implements DbEntity
{
    private String id;
    private String authenticationId;
    private long lastUpdated;
    private EliteInfo elite;
    
    @JsonIgnore
    public Duration getDurationSinceLastUpdate()
    {
        return Duration.between(Instant.ofEpochSecond(lastUpdated), Instant.now());
    }

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public DefinedTable getTable()
    {
        return DefinedTable.MEMBERS;
    }
    
    public static TSCGMember fromUserSnowflake(UserSnowflake snowflake)
    {
        return get(snowflake.getId()).orElseThrow();
    }
    
    public static Optional<TSCGMember> get(String id)
    {
        return Optional.ofNullable(TSCGDatabase.instance().get(DefinedTable.MEMBERS, id, TSCGMember.class));
    }
}
