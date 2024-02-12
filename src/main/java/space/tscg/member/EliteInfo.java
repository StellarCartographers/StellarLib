/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.member;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import static space.tscg.misc.StaticUtil.*;

@Getter
@Builder(builderMethodName = "Builder")
@Jacksonized
public class EliteInfo
{
    @Builder.Default
    private String cmdrName  = _EMPTY;
    @Builder.Default
    private String EliteId   = _EMPTY;
    @Builder.Default
    private String carrierId = _EMPTY;
}
