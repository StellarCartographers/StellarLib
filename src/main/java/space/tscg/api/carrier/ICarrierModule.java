/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.api.carrier;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import elite.dangerous.fdev.Outfitting.*;

import space.tscg.carrrier.ModuleImpl;
import space.tscg.misc.FDevID;

@JsonDeserialize(as = ModuleImpl.class)
public interface ICarrierModule
{
    FDevID id();

    Category category();

    String name();

    Mount mount();

    Guidance guidance();

    int moduleClass();

    Rating rating();

    int cost();

    int stock();
}
