/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.api.domain;

import okhttp3.HttpUrl;

import java.net.URI;

import space.tscg.web.domain.Domain;

public interface APIEndpoint
{
    default String getTemplate()
    {
        return "%s%s";
    }

    HttpUrl toHttpUrl();

    HttpUrl toHttpUrl(Domain domain);

    URI toUri();

    URI toUri(Domain domain);;
}
