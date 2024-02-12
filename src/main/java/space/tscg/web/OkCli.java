/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.web;

import lombok.Builder;
import lombok.Getter;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;
import java.time.Duration;
import java.util.Optional;

import space.tscg.misc.json.StellarMapper;

final class OkCli
{
    private static final Duration    TIME   = Duration.ofMillis(10000);
    // !fr
    public static final OkHttpClient CLIENT = new OkHttpClient.Builder().followRedirects(true).connectTimeout(TIME).readTimeout(TIME).writeTimeout(TIME).build();
    // @f

    @Builder(builderClassName = "Builder")
    @Getter
    public static class HttpResponse
    {
        private int       code;
        @lombok.Builder.Default
        private String    body    = "";
        @lombok.Builder.Default
        private String    message = "";
        private Headers   headers;
        private boolean   isOk;
        private HttpState state;

        public static HttpResponse from(Response response)
        {
            HttpResponse.Builder builder = HttpResponse.builder().code(response.code()).state(States.fromCode(response.code())).isOk(response.isSuccessful()).headers(response.headers());
            if ((response.message() != null) || !response.message().isBlank())
            {
                builder.message(response.message());
            }
            try
            {
                builder.body(response.body().string());
            } catch (IOException e)
            {
                builder.message(e.getMessage());
            }
            return builder.build();
        }

        public HttpError toHttpError()
        {
            if (!isOk)
            {
                return new HttpError(state);
            }
            throw new UnsupportedOperationException("HttpResponse isOk() returned 'true'");
        }

        public <T> Optional<T> mapBody(Class<T> clzz)
        {
            return StellarMapper.get().asOptional(this.body, clzz);
        }
    }
}
