/**
 * Copyright (c) 2023  The Stellar Cartographers' Guild.
 *
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */
package space.tscg.misc.error;

import panda.std.Result;
import space.tscg.collections.Data;
import space.tscg.web.HttpError;

public class MemberNotFound
{
    public <T> Result<T, HttpError> error(String memberId)
    {
        return HttpError.notFound(Data.asLinkedHashMap()
            .add("exception", "MemberNotFound")
            .add("error", "No Member by DiscordID: " + memberId));
    }
}