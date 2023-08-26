package space.tscg.common.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CryptUtils
{
    public static PublicKey loadPublicKey(byte[] keyBytes)
    {
        try
        {
            KeyFactory     publicKeyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec    = new X509EncodedKeySpec(keyBytes);
            PublicKey      publicKey        = publicKeyFactory.generatePublic(publicKeySpec);
            return publicKey;
        } catch (Exception e)
        {
            return null;
        }
    }

    public static PrivateKey loadPrivateKey(byte[] keyBytes)
    {
        try
        {
            KeyFactory     privateKeyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec privateKeySpec    = new PKCS8EncodedKeySpec(keyBytes);
            PrivateKey     privateKey        = privateKeyFactory.generatePrivate(privateKeySpec);
            return privateKey;
        } catch (Exception e)
        {
            return null;
        }
    }
}
