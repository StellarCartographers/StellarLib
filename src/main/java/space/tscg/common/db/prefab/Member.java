package space.tscg.common.db.prefab;

import java.util.Optional;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;
import space.tscg.common.db.modal.DbEntity;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "Builder", toBuilder = true)
@Jacksonized
public class Member implements DbEntity
{
    public static final String TABLE_NAME = "users";
    
    private String id;
    private String authenticationId;
    private EliteInfo elite;

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public String getTableName()
    {
        return TABLE_NAME;
    }

    public static Optional<Member> get(String id)
    {
        return Optional.ofNullable(TSCGDatabase.instance().get(TABLE_NAME, id, Member.class));
    }
}
