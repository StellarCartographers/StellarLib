/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.carrrier;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import space.tscg.api.carrier.ITaxableService;
import space.tscg.collections.DiffMap;

@Getter
@Builder(builderMethodName = "Creator")
@Jacksonized
public class TaxableService implements ITaxableService
{
    private boolean enabled;
    private int     taxRate;

    @Override
    public DiffMap diff(ITaxableService other)
    {
        return DiffMap.Builder().append("enabled", this.enabled(), other.enabled()).append("tax", this.taxRate(), other.taxRate()).build();
    }
}
