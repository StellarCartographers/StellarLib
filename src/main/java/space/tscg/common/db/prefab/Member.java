package space.tscg.common.db.prefab;

import static com.rethinkdb.RethinkDB.r;

import java.util.Optional;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;
import space.tscg.common.db.modal.DbEntity;
import space.tscg.common.db.op.Operation;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "Builder", toBuilder = true)
@Jacksonized
public class Member extends DbEntity
{
    public static final String TABLE_NAME = "users";
    
    private String discordId;
    private String authenticationId;
    private EliteInfo elite;

    @Override
    public String getId()
    {
        return discordId;
    }

    @Override
    public String getTableName()
    {
        return TABLE_NAME;
    }
    
    public Operation update()
    {
        return TSCGDatabase.get().update(this);
    }

    public static Optional<Member> get(String id)
    {
        return Optional.ofNullable(r.table(TABLE_NAME).get(id).runAtom(TSCGDatabase.getConn(), Member.class));
    }
    
    public static Operation delete(String uuid)
    {
        return r.table(TABLE_NAME).get(uuid).delete().run(TSCGDatabase.getConn(), Operation.class).single();
    }
}
