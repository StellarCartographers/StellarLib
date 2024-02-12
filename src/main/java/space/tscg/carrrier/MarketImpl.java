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

import java.util.List;

import space.tscg.api.carrier.ICarrierMarket;
import space.tscg.api.carrier.ICarrierModule;
import space.tscg.api.carrier.ICarrierShip;

@Getter
@Builder(builderMethodName = "Builder")
@Jacksonized
public class MarketImpl implements ICarrierMarket
{
    private List<ICarrierShip>   ships;
    private List<ICarrierModule> modules;
}
