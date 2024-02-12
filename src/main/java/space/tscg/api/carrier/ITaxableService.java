/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.api.carrier;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import space.tscg.api.Diffable;
import space.tscg.carrrier.TaxableService;

/**
 * A tax-rate configurable service that is accessible on fleetcarriers
 */
@JsonDeserialize(as = TaxableService.class)
public interface ITaxableService extends Diffable<ITaxableService>
{
    /**
     * If this service is installed and publically available
     * 
     * @return TRUE if, and only if, the service is PUBLIC
     */
    boolean enabled();

    int taxRate();
}
