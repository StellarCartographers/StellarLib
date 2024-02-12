/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.member;

import lombok.*;

import com.fasterxml.jackson.databind.annotation.*;

import space.tscg.member.FrontierAuth.AuthenticationBuilder;
import space.tscg.misc.encryption.*;

@Data
@JsonDeserialize(builder = AuthenticationBuilder.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FrontierAuth
{
    private String accessToken;
    private String refreshToken;
    private long   expiresAt;

    FrontierAuth(AuthenticationBuilder builder)
    {
        this.accessToken = builder.accessToken();
        this.refreshToken = builder.refreshToken();
        this.expiresAt = builder.expiresAt();
    }

    public static FrontierAuth newKeys(String accessToken, String refreshToken, long expiresAt)
    {
        return new FrontierAuth(EncryptedKey.of(accessToken, KeyType.ACCESS).getKey(), EncryptedKey.of(refreshToken, KeyType.REFRESH).getKey(), expiresAt);
    }

    public String getRefreshToken()
    {
        return EncryptedKey.of(refreshToken, KeyType.REFRESH).getKey();
    }

    public String getAccessToken()
    {
        return EncryptedKey.of(accessToken, KeyType.ACCESS).getKey();
    }

    public static AuthenticationBuilder Builder()
    {
        return new AuthenticationBuilder();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    @JsonPOJOBuilder(withPrefix = "")
    public static class AuthenticationBuilder
    {
        private String accessToken;
        private String refreshToken;
        private long   expiresAt;

        public AuthenticationBuilder accessToken(final String accessToken)
        {
            this.accessToken = EncryptedKey.of(accessToken, KeyType.ACCESS).getKey();
            return this;
        }

        public AuthenticationBuilder refreshToken(final String refreshToken)
        {
            this.refreshToken = EncryptedKey.of(refreshToken, KeyType.REFRESH).getKey();
            return this;
        }

        public AuthenticationBuilder expiresAt(final long expiresAt)
        {
            this.expiresAt = expiresAt;
            return this;
        }

        public FrontierAuth build()
        {
            return new FrontierAuth(this);
        }
    }
}
