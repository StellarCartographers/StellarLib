package space.tscg.common.http;

import panda.std.Result;

public record HttpError(HttpState state, Data data)
{
    public static <T> Result<T, HttpError> unauthorized(Data data) {
        return Result.error(new HttpError(HttpState.UNAUTHORIZED, data));
    }

    public static <T> Result<T, HttpError> forbidden(Data data) {
        return Result.error(new HttpError(HttpState.FORBIDDEN, data));
    }

    public static <T> Result<T, HttpError> badRequest(Data data) {
        return Result.error(new HttpError(HttpState.BAD_REQUEST, data));
    }

    public static <T> Result<T, HttpError> notFound(Data data) {
        return Result.error(new HttpError(HttpState.NOT_FOUND, data));
    }

    public static <T> Result<T, HttpError> conflict(Data data) {
        return Result.error(new HttpError(HttpState.CONFLICT, data));
    }

    public static <T> Result<T, HttpError> internalServerError(Data data) {
        return Result.error(new HttpError(HttpState.INTERNAL_SERVER_ERROR, data));
    }
    
    public static <T> Result<T, HttpError> unauthorized() {
        return Result.error(new HttpError(HttpState.UNAUTHORIZED, null));
    }

    public static <T> Result<T, HttpError> forbidden() {
        return Result.error(new HttpError(HttpState.FORBIDDEN, null));
    }

    public static <T> Result<T, HttpError> badRequest() {
        return Result.error(new HttpError(HttpState.BAD_REQUEST, null));
    }

    public static <T> Result<T, HttpError> notFound() {
        return Result.error(new HttpError(HttpState.NOT_FOUND, null));
    }

    public static <T> Result<T, HttpError> conflict() {
        return Result.error(new HttpError(HttpState.CONFLICT, null));
    }

    public static <T> Result<T, HttpError> internalServerError() {
        return Result.error(new HttpError(HttpState.INTERNAL_SERVER_ERROR, null));
    }
}
