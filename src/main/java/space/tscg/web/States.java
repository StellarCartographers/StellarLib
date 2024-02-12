/*
 * This file is part of StellarLib, licensed under the GNU GPL v3.0.
 * Copyright (C) 2023 StellarCartographers.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0-standalone.html>.
 */
package space.tscg.web;

import java.util.HashMap;
import java.util.Map;

public final class States
{
    public static final HttpState          CONTINUE                        = new HttpState(100, "Continue");
    public static final HttpState          SWITCHING_PROTOCOLS             = new HttpState(101, "Switching Protocols");
    public static final HttpState          PROCESSING                      = new HttpState(102, "Processing");
    public static final HttpState          EARLY_HINTS                     = new HttpState(103, "Early Hints");
    public static final HttpState          OK                              = new HttpState(200, "OK");
    public static final HttpState          CREATED                         = new HttpState(201, "Created");
    public static final HttpState          ACCEPTED                        = new HttpState(202, "Accepted");
    public static final HttpState          NON_AUTHORITATIVE_INFORMATION   = new HttpState(203, "Non Authoritative Information");
    public static final HttpState          NO_CONTENT                      = new HttpState(204, "No Content");
    public static final HttpState          RESET_CONTENT                   = new HttpState(205, "Reset Content");
    public static final HttpState          PARTIAL_CONTENT                 = new HttpState(206, "Partial Content");
    public static final HttpState          MULTI_STATUS                    = new HttpState(207, "Multi-Status");
    public static final HttpState          ALREADY_REPORTED                = new HttpState(208, "Already Reported");
    public static final HttpState          IM_USED                         = new HttpState(226, "IM Used");
    public static final HttpState          MULTIPLE_CHOICES                = new HttpState(300, "Multiple Choices");
    public static final HttpState          MOVED_PERMANENTLY               = new HttpState(301, "Moved Permanently");
    public static final HttpState          FOUND                           = new HttpState(302, "Found");
    public static final HttpState          SEE_OTHER                       = new HttpState(303, "See Other");
    public static final HttpState          NOT_MODIFIED                    = new HttpState(304, "Not Modified");
    public static final HttpState          USE_PROXY                       = new HttpState(305, "Use Proxy");
    public static final HttpState          TEMPORARY_REDIRECT              = new HttpState(307, "Temporary Redirect");
    public static final HttpState          PERMANENT_REDIRECT              = new HttpState(308, "Permanent Redirect");
    public static final HttpState          BAD_REQUEST                     = new HttpState(400, "Bad Request");
    public static final HttpState          UNAUTHORIZED                    = new HttpState(401, "Unauthorized");
    public static final HttpState          PAYMENT_REQUIRED                = new HttpState(402, "Payment Required");
    public static final HttpState          FORBIDDEN                       = new HttpState(403, "Forbidden");
    public static final HttpState          NOT_FOUND                       = new HttpState(404, "Not Found");
    public static final HttpState          METHOD_NOT_ALLOWED              = new HttpState(405, "Method Not Allowed");
    public static final HttpState          NOT_ACCEPTABLE                  = new HttpState(406, "Not Acceptable");
    public static final HttpState          PROXY_AUTHENTICATION_REQUIRED   = new HttpState(407, "Proxy Authentication Required");
    public static final HttpState          REQUEST_TIMEOUT                 = new HttpState(408, "Request Timeout");
    public static final HttpState          CONFLICT                        = new HttpState(409, "Conflict");
    public static final HttpState          GONE                            = new HttpState(410, "Gone");
    public static final HttpState          LENGTH_REQUIRED                 = new HttpState(411, "Length Required");
    public static final HttpState          PRECONDITION_FAILED             = new HttpState(412, "Precondition Failed");
    public static final HttpState          CONTENT_TOO_LARGE               = new HttpState(413, "Content Too Large");
    public static final HttpState          URI_TOO_LONG                    = new HttpState(414, "URI Too Long");
    public static final HttpState          UNSUPPORTED_MEDIA_TYPE          = new HttpState(415, "Unsupported Media Type");
    public static final HttpState          RANGE_NOT_SATISFIABLE           = new HttpState(416, "Range Not Satisfiable");
    public static final HttpState          EXPECTATION_FAILED              = new HttpState(417, "Expectation Failed");
    public static final HttpState          IM_A_TEAPOT                     = new HttpState(418, "I'm a Teapot");
    public static final HttpState          ENHANCE_YOUR_CALM               = new HttpState(420, "Enhance your Calm");
    public static final HttpState          MISDIRECTED_REQUEST             = new HttpState(421, "Misdirected Request");
    public static final HttpState          UNPROCESSABLE_CONTENT           = new HttpState(422, "Unprocessable Content");
    public static final HttpState          LOCKED                          = new HttpState(423, "Locked");
    public static final HttpState          FAILED_DEPENDENCY               = new HttpState(424, "Failed Dependency");
    public static final HttpState          TOO_EARLY                       = new HttpState(425, "Too Early");
    public static final HttpState          UPGRADE_REQUIRED                = new HttpState(426, "Upgrade Required");
    public static final HttpState          PRECONDITION_REQUIRED           = new HttpState(428, "Precondition Required");
    public static final HttpState          TOO_MANY_REQUESTS               = new HttpState(429, "Too Many Requests");
    public static final HttpState          REQUEST_HEADER_FIELDS_TOO_LARGE = new HttpState(431, "Request Header Fields Too Large");
    public static final HttpState          UNAVAILABLE_FOR_LEGAL_REASONS   = new HttpState(451, "Unavailable for Legal Reason");
    public static final HttpState          INTERNAL_SERVER_ERROR           = new HttpState(500, "Server Error");
    public static final HttpState          NOT_IMPLEMENTED                 = new HttpState(501, "Not Implemented");
    public static final HttpState          BAD_GATEWAY                     = new HttpState(502, "Bad Gateway");
    public static final HttpState          SERVICE_UNAVAILABLE             = new HttpState(503, "Service Unavailable");
    public static final HttpState          GATEWAY_TIMEOUT                 = new HttpState(504, "Gateway Timeout");
    public static final HttpState          HTTP_VERSION_NOT_SUPPORTED      = new HttpState(505, "HTTP Version Not Supported");
    public static final HttpState          INSUFFICIENT_STORAGE            = new HttpState(507, "Insufficient Storage");
    public static final HttpState          LOOP_DETECTED                   = new HttpState(508, "Loop Detected");
    private static Map<Integer, HttpState> CODE_TO_STATES                  = new HashMap<>();
    static
    {
        CODE_TO_STATES.put(CONTINUE.code(), CONTINUE);
        CODE_TO_STATES.put(SWITCHING_PROTOCOLS.code(), SWITCHING_PROTOCOLS);
        CODE_TO_STATES.put(PROCESSING.code(), PROCESSING);
        CODE_TO_STATES.put(EARLY_HINTS.code(), EARLY_HINTS);
        CODE_TO_STATES.put(OK.code(), OK);
        CODE_TO_STATES.put(CREATED.code(), CREATED);
        CODE_TO_STATES.put(ACCEPTED.code(), ACCEPTED);
        CODE_TO_STATES.put(NON_AUTHORITATIVE_INFORMATION.code(), NON_AUTHORITATIVE_INFORMATION);
        CODE_TO_STATES.put(NO_CONTENT.code(), NO_CONTENT);
        CODE_TO_STATES.put(RESET_CONTENT.code(), RESET_CONTENT);
        CODE_TO_STATES.put(PARTIAL_CONTENT.code(), PARTIAL_CONTENT);
        CODE_TO_STATES.put(MULTI_STATUS.code(), MULTI_STATUS);
        CODE_TO_STATES.put(ALREADY_REPORTED.code(), ALREADY_REPORTED);
        CODE_TO_STATES.put(IM_USED.code(), IM_USED);
        CODE_TO_STATES.put(MULTIPLE_CHOICES.code(), MULTIPLE_CHOICES);
        CODE_TO_STATES.put(MOVED_PERMANENTLY.code(), MOVED_PERMANENTLY);
        CODE_TO_STATES.put(FOUND.code(), FOUND);
        CODE_TO_STATES.put(SEE_OTHER.code(), SEE_OTHER);
        CODE_TO_STATES.put(NOT_MODIFIED.code(), NOT_MODIFIED);
        CODE_TO_STATES.put(USE_PROXY.code(), USE_PROXY);
        CODE_TO_STATES.put(TEMPORARY_REDIRECT.code(), TEMPORARY_REDIRECT);
        CODE_TO_STATES.put(PERMANENT_REDIRECT.code(), PERMANENT_REDIRECT);
        CODE_TO_STATES.put(BAD_REQUEST.code(), BAD_REQUEST);
        CODE_TO_STATES.put(UNAUTHORIZED.code(), UNAUTHORIZED);
        CODE_TO_STATES.put(PAYMENT_REQUIRED.code(), PAYMENT_REQUIRED);
        CODE_TO_STATES.put(FORBIDDEN.code(), FORBIDDEN);
        CODE_TO_STATES.put(NOT_FOUND.code(), NOT_FOUND);
        CODE_TO_STATES.put(METHOD_NOT_ALLOWED.code(), METHOD_NOT_ALLOWED);
        CODE_TO_STATES.put(NOT_ACCEPTABLE.code(), NOT_ACCEPTABLE);
        CODE_TO_STATES.put(PROXY_AUTHENTICATION_REQUIRED.code(), PROXY_AUTHENTICATION_REQUIRED);
        CODE_TO_STATES.put(REQUEST_TIMEOUT.code(), REQUEST_TIMEOUT);
        CODE_TO_STATES.put(CONFLICT.code(), CONFLICT);
        CODE_TO_STATES.put(GONE.code(), GONE);
        CODE_TO_STATES.put(LENGTH_REQUIRED.code(), LENGTH_REQUIRED);
        CODE_TO_STATES.put(PRECONDITION_FAILED.code(), PRECONDITION_FAILED);
        CODE_TO_STATES.put(CONTENT_TOO_LARGE.code(), CONTENT_TOO_LARGE);
        CODE_TO_STATES.put(URI_TOO_LONG.code(), URI_TOO_LONG);
        CODE_TO_STATES.put(UNSUPPORTED_MEDIA_TYPE.code(), UNSUPPORTED_MEDIA_TYPE);
        CODE_TO_STATES.put(RANGE_NOT_SATISFIABLE.code(), RANGE_NOT_SATISFIABLE);
        CODE_TO_STATES.put(EXPECTATION_FAILED.code(), EXPECTATION_FAILED);
        CODE_TO_STATES.put(IM_A_TEAPOT.code(), IM_A_TEAPOT);
        CODE_TO_STATES.put(ENHANCE_YOUR_CALM.code(), ENHANCE_YOUR_CALM);
        CODE_TO_STATES.put(MISDIRECTED_REQUEST.code(), MISDIRECTED_REQUEST);
        CODE_TO_STATES.put(UNPROCESSABLE_CONTENT.code(), UNPROCESSABLE_CONTENT);
        CODE_TO_STATES.put(LOCKED.code(), LOCKED);
        CODE_TO_STATES.put(FAILED_DEPENDENCY.code(), FAILED_DEPENDENCY);
        CODE_TO_STATES.put(TOO_EARLY.code(), TOO_EARLY);
        CODE_TO_STATES.put(UPGRADE_REQUIRED.code(), UPGRADE_REQUIRED);
        CODE_TO_STATES.put(PRECONDITION_REQUIRED.code(), PRECONDITION_REQUIRED);
        CODE_TO_STATES.put(TOO_MANY_REQUESTS.code(), TOO_MANY_REQUESTS);
        CODE_TO_STATES.put(REQUEST_HEADER_FIELDS_TOO_LARGE.code(), REQUEST_HEADER_FIELDS_TOO_LARGE);
        CODE_TO_STATES.put(UNAVAILABLE_FOR_LEGAL_REASONS.code(), UNAVAILABLE_FOR_LEGAL_REASONS);
        CODE_TO_STATES.put(INTERNAL_SERVER_ERROR.code(), INTERNAL_SERVER_ERROR);
        CODE_TO_STATES.put(NOT_IMPLEMENTED.code(), NOT_IMPLEMENTED);
        CODE_TO_STATES.put(BAD_GATEWAY.code(), BAD_GATEWAY);
        CODE_TO_STATES.put(SERVICE_UNAVAILABLE.code(), SERVICE_UNAVAILABLE);
        CODE_TO_STATES.put(GATEWAY_TIMEOUT.code(), GATEWAY_TIMEOUT);
        CODE_TO_STATES.put(HTTP_VERSION_NOT_SUPPORTED.code(), HTTP_VERSION_NOT_SUPPORTED);
        CODE_TO_STATES.put(INSUFFICIENT_STORAGE.code(), INSUFFICIENT_STORAGE);
        CODE_TO_STATES.put(LOOP_DETECTED.code(), LOOP_DETECTED);
    }

    public static HttpState fromCode(int code)
    {
        return CODE_TO_STATES.get(code);
    }
}
