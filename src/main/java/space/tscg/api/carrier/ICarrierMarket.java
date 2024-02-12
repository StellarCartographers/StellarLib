/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.api.carrier;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

import space.tscg.carrrier.MarketImpl;

@JsonDeserialize(as = MarketImpl.class)
public interface ICarrierMarket
{
    List<ICarrierShip> ships();

    List<ICarrierModule> modules();
}
