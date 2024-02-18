/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.carrrier;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import org.jetbrains.annotations.Nullable;

import elite.dangerous.fdev.Outfitting.*;
import elite.dangerous.model.identity.ID;

import space.tscg.api.carrier.ICarrierModule;

@Getter
@Builder(builderMethodName = "Builder")
@Jacksonized
public class ModuleImpl implements ICarrierModule
{
    private ID   id;
    private Category category;
    private String   name;
    @Nullable
    @Builder.Default
    private Mount    mount    = null;
    @Nullable
    @Builder.Default
    private Guidance guidance = null;
    private int      moduleClass;
    private Rating   rating;
    private int      cost;
    private int      stock;
}
