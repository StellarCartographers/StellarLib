/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.web;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public final class Body
{
    public static RequestBody json(Object obj)
    {
        return RequestBody.create(obj.toString(), MediaType.get("application/json; charset=utf-8"));
    }
}
