package space.tscg.api.auth;

public interface AuthEntity
{
    String retrieveAccessKey();
    
    String retrieveRefreshToken();
    
    boolean isAccessTokenExpired();
}
