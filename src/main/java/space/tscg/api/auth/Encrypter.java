package space.tscg.api.auth;

public interface Encrypter
{
    String encode(String toEncode);
    
    String decode(String toDecode);
}
