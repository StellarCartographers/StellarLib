/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.carrrier;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import space.tscg.api.carrier.IService;
import space.tscg.collections.DiffMap;

@Getter
@Builder(builderMethodName = "Creator")
@Jacksonized
public class Service implements IService
{
    private boolean enabled;

    @Override
    public DiffMap diff(IService other)
    {
        return DiffMap.Builder().append("active", this.enabled(), other.enabled()).build();
    }
}
