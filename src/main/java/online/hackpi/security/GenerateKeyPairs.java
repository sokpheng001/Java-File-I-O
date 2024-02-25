package online.hackpi.security;

import online.hackpi.service.ServiceImp;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

public class GenerateKeyPairs {
    private final ServiceImp serviceImp = new ServiceImp();
    private static final Map<String, Key> keyPairs = new HashMap<>();
    private static String encrypt(String data, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher cipher  = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte [] encryptBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptBytes);
    }
    private static String decrypt(String encryptedData, PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte [ ] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        byte [] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
    public static Map<String, Key> getKeyPairs(){
        try{
            KeyPairGenerator keyPairGenerator  = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2084);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey1  = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(
                    Base64.getDecoder().decode(Base64.getEncoder().encodeToString(publicKey.getEncoded()))));
            PrivateKey privateKey1 = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(
                    Base64.getDecoder().decode(Base64.getEncoder().encodeToString(privateKey.getEncoded()))));
            keyPairs.put("public_key",publicKey1);
            keyPairs.put("private_key", privateKey1);
        }catch (Exception exception){
            System.err.println("Problem during generating key pairs: " + exception.getMessage());
        }
        return keyPairs;
    }
}
