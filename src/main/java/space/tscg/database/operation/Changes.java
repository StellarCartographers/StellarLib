/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.database.operation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Changes extends ArrayList<Change>
{
    private static final long serialVersionUID = 7624942674057465324L;

    @JsonIgnore
    public Change getFirst()
    {
        if (this.size() >= 1)
        {
            return this.get(0);
        }
        return new Change();
    }
}
