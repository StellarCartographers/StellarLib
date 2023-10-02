package space.tscg.common.db.prefab;

import java.util.Optional;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;
import space.tscg.api.database.DbEntity;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "Builder", toBuilder = true)
@Jacksonized
public class Member implements DbEntity
{
    private String id;
    private String authenticationId;
    private EliteInfo elite;

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public Tables getTable()
    {
        return Tables.MEMBERS;
    }

    public static Optional<Member> get(String id)
    {
        return Optional.ofNullable(TSCGDatabase.instance().get(Tables.MEMBERS.toString(), id, Member.class));
    }
}
