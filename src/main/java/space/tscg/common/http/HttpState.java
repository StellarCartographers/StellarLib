package space.tscg.common.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import io.javalin.http.HttpStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class HttpState
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
        CODE_TO_STATES.put(CONTINUE.getCode(), CONTINUE);
        CODE_TO_STATES.put(SWITCHING_PROTOCOLS.getCode(), SWITCHING_PROTOCOLS);
        CODE_TO_STATES.put(PROCESSING.getCode(), PROCESSING);
        CODE_TO_STATES.put(EARLY_HINTS.getCode(), EARLY_HINTS);
        CODE_TO_STATES.put(OK.getCode(), OK);
        CODE_TO_STATES.put(CREATED.getCode(), CREATED);
        CODE_TO_STATES.put(ACCEPTED.getCode(), ACCEPTED);
        CODE_TO_STATES.put(NON_AUTHORITATIVE_INFORMATION.getCode(), NON_AUTHORITATIVE_INFORMATION);
        CODE_TO_STATES.put(NO_CONTENT.getCode(), NO_CONTENT);
        CODE_TO_STATES.put(RESET_CONTENT.getCode(), RESET_CONTENT);
        CODE_TO_STATES.put(PARTIAL_CONTENT.getCode(), PARTIAL_CONTENT);
        CODE_TO_STATES.put(MULTI_STATUS.getCode(), MULTI_STATUS);
        CODE_TO_STATES.put(ALREADY_REPORTED.getCode(), ALREADY_REPORTED);
        CODE_TO_STATES.put(IM_USED.getCode(), IM_USED);
        CODE_TO_STATES.put(MULTIPLE_CHOICES.getCode(), MULTIPLE_CHOICES);
        CODE_TO_STATES.put(MOVED_PERMANENTLY.getCode(), MOVED_PERMANENTLY);
        CODE_TO_STATES.put(FOUND.getCode(), FOUND);
        CODE_TO_STATES.put(SEE_OTHER.getCode(), SEE_OTHER);
        CODE_TO_STATES.put(NOT_MODIFIED.getCode(), NOT_MODIFIED);
        CODE_TO_STATES.put(USE_PROXY.getCode(), USE_PROXY);
        CODE_TO_STATES.put(TEMPORARY_REDIRECT.getCode(), TEMPORARY_REDIRECT);
        CODE_TO_STATES.put(PERMANENT_REDIRECT.getCode(), PERMANENT_REDIRECT);
        CODE_TO_STATES.put(BAD_REQUEST.getCode(), BAD_REQUEST);
        CODE_TO_STATES.put(UNAUTHORIZED.getCode(), UNAUTHORIZED);
        CODE_TO_STATES.put(PAYMENT_REQUIRED.getCode(), PAYMENT_REQUIRED);
        CODE_TO_STATES.put(FORBIDDEN.getCode(), FORBIDDEN);
        CODE_TO_STATES.put(NOT_FOUND.getCode(), NOT_FOUND);
        CODE_TO_STATES.put(METHOD_NOT_ALLOWED.getCode(), METHOD_NOT_ALLOWED);
        CODE_TO_STATES.put(NOT_ACCEPTABLE.getCode(), NOT_ACCEPTABLE);
        CODE_TO_STATES.put(PROXY_AUTHENTICATION_REQUIRED.getCode(), PROXY_AUTHENTICATION_REQUIRED);
        CODE_TO_STATES.put(REQUEST_TIMEOUT.getCode(), REQUEST_TIMEOUT);
        CODE_TO_STATES.put(CONFLICT.getCode(), CONFLICT);
        CODE_TO_STATES.put(GONE.getCode(), GONE);
        CODE_TO_STATES.put(LENGTH_REQUIRED.getCode(), LENGTH_REQUIRED);
        CODE_TO_STATES.put(PRECONDITION_FAILED.getCode(), PRECONDITION_FAILED);
        CODE_TO_STATES.put(CONTENT_TOO_LARGE.getCode(), CONTENT_TOO_LARGE);
        CODE_TO_STATES.put(URI_TOO_LONG.getCode(), URI_TOO_LONG);
        CODE_TO_STATES.put(UNSUPPORTED_MEDIA_TYPE.getCode(), UNSUPPORTED_MEDIA_TYPE);
        CODE_TO_STATES.put(RANGE_NOT_SATISFIABLE.getCode(), RANGE_NOT_SATISFIABLE);
        CODE_TO_STATES.put(EXPECTATION_FAILED.getCode(), EXPECTATION_FAILED);
        CODE_TO_STATES.put(IM_A_TEAPOT.getCode(), IM_A_TEAPOT);
        CODE_TO_STATES.put(ENHANCE_YOUR_CALM.getCode(), ENHANCE_YOUR_CALM);
        CODE_TO_STATES.put(MISDIRECTED_REQUEST.getCode(), MISDIRECTED_REQUEST);
        CODE_TO_STATES.put(UNPROCESSABLE_CONTENT.getCode(), UNPROCESSABLE_CONTENT);
        CODE_TO_STATES.put(LOCKED.getCode(), LOCKED);
        CODE_TO_STATES.put(FAILED_DEPENDENCY.getCode(), FAILED_DEPENDENCY);
        CODE_TO_STATES.put(TOO_EARLY.getCode(), TOO_EARLY);
        CODE_TO_STATES.put(UPGRADE_REQUIRED.getCode(), UPGRADE_REQUIRED);
        CODE_TO_STATES.put(PRECONDITION_REQUIRED.getCode(), PRECONDITION_REQUIRED);
        CODE_TO_STATES.put(TOO_MANY_REQUESTS.getCode(), TOO_MANY_REQUESTS);
        CODE_TO_STATES.put(REQUEST_HEADER_FIELDS_TOO_LARGE.getCode(), REQUEST_HEADER_FIELDS_TOO_LARGE);
        CODE_TO_STATES.put(UNAVAILABLE_FOR_LEGAL_REASONS.getCode(), UNAVAILABLE_FOR_LEGAL_REASONS);
        CODE_TO_STATES.put(INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR);
        CODE_TO_STATES.put(NOT_IMPLEMENTED.getCode(), NOT_IMPLEMENTED);
        CODE_TO_STATES.put(BAD_GATEWAY.getCode(), BAD_GATEWAY);
        CODE_TO_STATES.put(SERVICE_UNAVAILABLE.getCode(), SERVICE_UNAVAILABLE);
        CODE_TO_STATES.put(GATEWAY_TIMEOUT.getCode(), GATEWAY_TIMEOUT);
        CODE_TO_STATES.put(HTTP_VERSION_NOT_SUPPORTED.getCode(), HTTP_VERSION_NOT_SUPPORTED);
        CODE_TO_STATES.put(INSUFFICIENT_STORAGE.getCode(), INSUFFICIENT_STORAGE);
        CODE_TO_STATES.put(LOOP_DETECTED.getCode(), LOOP_DETECTED);
    }

    public static HttpState fromCode(int code)
    {
        return CODE_TO_STATES.get(code);
    }

    private final int    code;
    private final String state;
    @SuppressWarnings("rawtypes")
    private Data         data;

    public HttpStatus toHttpStatus()
    {
        return HttpStatus.forStatus(code);
    }

    public <T> HttpState withData(Data<T> data)
    {
        this.data = data;
        return this;
    }

    @UtilityClass
    public static final class HttpStateAdapter {
        
        public static class Serializer extends StdSerializer<HttpState> {

            private static final long serialVersionUID = 9115662983758042660L;

            protected Serializer()
            {
                super(HttpState.class);
            }

            @Override
            public void serialize(HttpState value, JsonGenerator gen, SerializerProvider provider) throws IOException
            {
            }
        }
        
        public static class Deserializer extends StdDeserializer<HttpState> {

            private static final long serialVersionUID = 5873054711350065553L;

            protected Deserializer()
            {
                super(HttpState.class);
            }

            @Override
            public HttpState deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException
            {
                return null;
            }
            
        }
    }
}
