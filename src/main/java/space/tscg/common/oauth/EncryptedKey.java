package space.tscg.common.oauth;

import java.beans.ConstructorProperties;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import space.tscg.api.auth.Encrypter;
import space.tscg.api.auth.Key;

public class EncryptedKey<T> implements Key<T>
{
    private final String encryptedKey;
    @JsonIgnore
    private final Encrypter encrypter;
    
    @JsonCreator
    @ConstructorProperties({"key"})
    private EncryptedKey(@JsonProperty("key") String key, Encrypter encrypter)
    {
        this.encryptedKey = encrypter.encode(key);
        this.encrypter = encrypter;
    }

    @Override
    public String decode()
    {
        return encrypter.decode(encryptedKey);
    }

    @Override
    public Optional<T> decodeAsClass(Class<T> clz)
    {
        try
        {
            return Optional.ofNullable(clz.getDeclaredConstructor(String.class).newInstance(decode()));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
        {
            return Optional.empty();
        }
    }
}
