package space.tscg.api.auth;

import java.util.Optional;

public interface Key<T>
{
    String decode();
    
    Optional<T> decodeAsClass(Class<T> clz);
}
