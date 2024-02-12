/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.database.operation;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class DeleteOperation implements Operation<DeleteOperation>
{
    private int           deleted, skipped, errors;
    @JsonProperty("first_error")
    private String        firstError;
    private final int     inserted  = 0;
    private final int     replaced  = 0;
    private final int     unchanged = 0;
    private final Changes changes   = new Changes();

    @Override
    public boolean operationSucceded()
    {
        return (deleted > 0) && (skipped == 0) && (errors == 0);
    }

    @Override
    public DeleteOperation get()
    {
        return this;
    }
}
