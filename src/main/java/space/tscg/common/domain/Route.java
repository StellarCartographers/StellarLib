package space.tscg.common.domain;

import lombok.Getter;

@Getter
public enum Route
{
    CARRIER("/api/carrier"),
    OAUTH("/api/ouath"),
    CAPI("/internal/capi")
    ;

    String route;
    
    Route(String string)
    {
        this.route = string;
    }
}
