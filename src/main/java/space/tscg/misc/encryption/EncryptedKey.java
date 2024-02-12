/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.misc.encryption;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import com.nimbusds.oauth2.sdk.token.RefreshToken;

import java.beans.ConstructorProperties;

public final class EncryptedKey
{
    private final String  key;
    private final KeyType type;

    @JsonCreator
    @ConstructorProperties({"key", "type"})
    private EncryptedKey(@JsonProperty("key") String key, @JsonProperty("type") KeyType type)
    {
        this.key = EncryptDecrypt.encode(key);
        this.type = type;
    }

    public static EncryptedKey of(String key, KeyType type)
    {
        return new EncryptedKey(key, type);
    }

    public String getKey()
    {
        return EncryptDecrypt.decode(key);
    }

    public KeyType getType()
    {
        return type;
    }

    public BearerAccessToken asBearerAccessToken()
    {
        if (getType().equals(KeyType.ACCESS))
            return new BearerAccessToken(key);
        throw new RuntimeException();
    }

    public RefreshToken asRefreshToken()
    {
        if (getType().equals(KeyType.REFRESH))
            return new RefreshToken(key);
        throw new RuntimeException();
    }
}
