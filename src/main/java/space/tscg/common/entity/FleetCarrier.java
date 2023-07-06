/**
 * Copyright (C) 2023  The Stellar Cartographers' Guild
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package space.tscg.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.tscg.common.util.Diffable;
import space.tscg.common.util.UpdatedValues;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "Builder", toBuilder = true)
public class FleetCarrier implements Diffable<FleetCarrier>
{

    private String   carrierId;
    private String   callsign;
    private String   vanityName;
    private String   currentStarSystem;
    private int      fuel;
    private Services services;

    @Data
    @Builder(builderMethodName = "Builder", toBuilder = true)
    public static class Services implements Diffable<Services>
    {

        private Refuel                 refuel;
        private Repair                 repair;
        private Armoury                armoury;
        private RedemptionOffice       redemptionOffice;
        private Shipyard               shipyard;
        private Outfitting             outfitting;
        private SecureWarehouse        secureWarehouse;
        private UniversalCartographics universalCartographics;
        private ConcourseBar           concourseBar;
        private VistaGenomics          vistaGenomics;
        private PioneerSupplies        pioneerSupplies;

        @Data
        @Builder(builderMethodName = "Builder", toBuilder = true)
        public static class Refuel implements Diffable<Refuel>
        {

            private boolean active;
            private int     taxRate;

            /**
             * {@inheritDoc}
             */
            @Override
            public UpdatedValues diff(Refuel other)
            {
                return UpdatedValues
                    .Builder()
                    .append("active", this.active, other.active)
                    .append("taxRate", this.taxRate, other.taxRate)
                    .buildUpdate();
            }
        }

        @Data
        @Builder(builderMethodName = "Builder", toBuilder = true)
        public static class Repair implements Diffable<Repair>
        {

            private boolean active;
            private int     taxRate;

            /**
             * {@inheritDoc}
             */
            @Override
            public UpdatedValues diff(Repair other)
            {
                return UpdatedValues
                    .Builder()
                    .append("active", this.active, other.active)
                    .append("taxRate", this.taxRate, other.taxRate)
                    .buildUpdate();
            }
        }

        @Data
        @Builder(builderMethodName = "Builder", toBuilder = true)
        public static class Armoury implements Diffable<Armoury>
        {

            private boolean active;
            private int     taxRate;

            /**
             * {@inheritDoc}
             */
            @Override
            public UpdatedValues diff(Armoury other)
            {
                return UpdatedValues
                    .Builder()
                    .append("active", this.active, other.active)
                    .append("taxRate", this.taxRate, other.taxRate)
                    .buildUpdate();
            }
        }

        @Data
        @Builder(builderMethodName = "Builder", toBuilder = true)
        public static class RedemptionOffice implements Diffable<RedemptionOffice>
        {

            private boolean active;

            /**
             * {@inheritDoc}
             */
            @Override
            public UpdatedValues diff(RedemptionOffice other)
            {
                return UpdatedValues
                    .Builder()
                    .append("active", this.active, other.active)
                    .buildUpdate();
            }
        }

        @Data
        @Builder(builderMethodName = "Builder", toBuilder = true)
        public static class Shipyard implements Diffable<Shipyard>
        {

            private boolean active;
            private int     taxRate;

            /**
             * {@inheritDoc}
             */
            @Override
            public UpdatedValues diff(Shipyard other)
            {
                return UpdatedValues
                    .Builder()
                    .append("active", this.active, other.active)
                    .append("taxRate", this.taxRate, other.taxRate)
                    .buildUpdate();
            }
        }

        @Data
        @Builder(builderMethodName = "Builder", toBuilder = true)
        public static class Outfitting implements Diffable<Outfitting>
        {

            private boolean active;
            private int     taxRate;

            /**
             * {@inheritDoc}
             */
            @Override
            public UpdatedValues diff(Outfitting other)
            {
                return UpdatedValues
                    .Builder()
                    .append("active", this.active, other.active)
                    .append("taxRate", this.taxRate, other.taxRate)
                    .buildUpdate();
            }
        }

        @Data
        @Builder(builderMethodName = "Builder", toBuilder = true)
        public static class SecureWarehouse implements Diffable<SecureWarehouse>
        {

            private boolean active;

            /**
             * {@inheritDoc}
             */
            @Override
            public UpdatedValues diff(SecureWarehouse other)
            {
                return UpdatedValues
                    .Builder()
                    .append("active", this.active, other.active)
                    .buildUpdate();
            }
        }

        @Data
        @Builder(builderMethodName = "Builder", toBuilder = true)
        public static class UniversalCartographics implements Diffable<UniversalCartographics>
        {

            private boolean active;

            /**
             * {@inheritDoc}
             */
            @Override
            public UpdatedValues diff(UniversalCartographics other)
            {
                return UpdatedValues
                    .Builder()
                    .append("active", this.active, other.active)
                    .buildUpdate();
            }
        }

        @Data
        @Builder(builderMethodName = "Builder", toBuilder = true)
        public static class ConcourseBar implements Diffable<ConcourseBar>
        {

            private boolean active;

            /**
             * {@inheritDoc}
             */
            @Override
            public UpdatedValues diff(ConcourseBar other)
            {
                return UpdatedValues
                    .Builder()
                    .append("active", this.active, other.active)
                    .buildUpdate();
            }
        }

        @Data
        @Builder(builderMethodName = "Builder", toBuilder = true)
        public static class VistaGenomics implements Diffable<VistaGenomics>
        {

            private boolean active;

            /**
             * {@inheritDoc}
             */
            @Override
            public UpdatedValues diff(VistaGenomics other)
            {
                return UpdatedValues
                    .Builder()
                    .append("active", this.active, other.active)
                    .buildUpdate();
            }
        }

        @Data
        @Builder(builderMethodName = "Builder", toBuilder = true)
        public static class PioneerSupplies implements Diffable<PioneerSupplies>
        {

            private boolean active;
            private int     taxRate;


            /**
             * {@inheritDoc}
             */
            @Override
            public UpdatedValues diff(PioneerSupplies other)
            {
                return UpdatedValues
                    .Builder()
                    .append("active", this.active, other.active)
                    .append("taxRate", this.taxRate, other.taxRate)
                    .buildUpdate();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public UpdatedValues diff(Services other)
        {
            return UpdatedValues
                .Builder()
                .appendDiff("refuel", this.refuel.diff(other.refuel))
                .appendDiff("repair", this.repair.diff(other.repair))
                .appendDiff("armoury", this.armoury.diff(other.armoury))
                .appendDiff("redemptionOffice", this.redemptionOffice.diff(redemptionOffice))
                .appendDiff("shipyard", this.shipyard.diff(other.shipyard))
                .appendDiff("outfitting", this.outfitting.diff(other.outfitting))
                .appendDiff("secureWarehouse", this.secureWarehouse.diff(other.secureWarehouse))
                .appendDiff("universalCartographics", this.universalCartographics.diff(other.universalCartographics))
                .appendDiff("concourseBar", this.concourseBar.diff(other.concourseBar))
                .appendDiff("vistaGenomics", this.vistaGenomics.diff(other.vistaGenomics))
                .appendDiff("pioneerSupplies", this.pioneerSupplies.diff(other.pioneerSupplies))
                .buildUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UpdatedValues diff(FleetCarrier other)
    {
        return UpdatedValues.Builder()
            .append("vanityName", this.vanityName, other.vanityName)
            .append("fuel", this.fuel, other.fuel)
            .append("currentSolarSystem", this.currentStarSystem, other.currentStarSystem)
            .appendDiff("services", this.services.diff(other.services))
            .buildUpdate();
    }
}
