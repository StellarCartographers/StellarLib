package space.tscg.common.db.prefab;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder(builderMethodName = "Builder", toBuilder = true)
@Jacksonized
public class EliteInfo
{
    private String cmdrName;
    private String EliteId;
    private String systemAddress;
    private String carrierId;
}
