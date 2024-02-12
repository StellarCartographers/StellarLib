/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.database.entity;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import net.dv8tion.jda.api.entities.UserSnowflake;
import panda.std.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.concurrent.CompletableFuture;

import space.tscg.api.Name;
import space.tscg.api.database.DbEntity;
import space.tscg.database.defined.TSCGDatabase;
import space.tscg.database.operation.UpdateOperation;
import space.tscg.member.*;

@Data
@Builder(builderMethodName = "Builder")
@JsonInclude(Include.ALWAYS)
@Jacksonized
public class TSCGMember implements DbEntity
{
    private String         id;
    private String         apiKey;
    private EliteInfo      elite;
    private FrontierAuth   authentication;
    private CarrierManager manager;

    @Override
    public String getId()
    {
        return id;
    }

    public CompletableFuture<UpdateOperation> setAsHidden(Name name)
    {
        manager.publicChannel().setHidden(name);
        CompletableFuture<UpdateOperation> future = new CompletableFuture<UpdateOperation>();
        TSCGDatabase.instance().executor().execute(() ->
        {
            try
            {
                future.complete(MemberTable.instance().update(this));
            } catch (Exception ex)
            {
                future.completeExceptionally(ex);
            }
        });
        return future;
    }

    public CompletableFuture<UpdateOperation> setAsVisable(Name name)
    {
        manager.publicChannel().setVisable(name);
        CompletableFuture<UpdateOperation> future = new CompletableFuture<UpdateOperation>();
        TSCGDatabase.instance().executor().execute(() ->
        {
            try
            {
                future.complete(MemberTable.instance().update(this));
            } catch (Exception ex)
            {
                future.completeExceptionally(ex);
            }
        });
        return future;
    }

    public static Result<TSCGMember, Blank> fromUserSnowflake(UserSnowflake snowflake)
    {
        return MemberTable.instance().get(snowflake.getId());
    }
}
