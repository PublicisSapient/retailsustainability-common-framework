package com.publicis.sapient.p2p.utils;

import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class EncryptionUtil {

    private static final String ALGO = "RSA";


    private KeyPair generateKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = new SecureRandom();
        keyPairGenerator.initialize(1024, random);
         return  keyPairGenerator.generateKeyPair();
    }

    public String encrypt(String data){
        String encryptedValue;
        try {
            byte[] contentBytes = data.getBytes();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, decodePrivateKey(Constants.PRIVATE_KEY));
            byte[] cipherContent = cipher.doFinal(contentBytes);
            encryptedValue = Base64.getEncoder().encodeToString(cipherContent);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return encryptedValue;
    }

    public String encryptUsingPublic(String data){
        String encryptedValue;
        try {
            byte[] contentBytes = data.getBytes();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, decodePublicKey(Constants.PUBLIC_KEY));
            byte[] cipherContent = cipher.doFinal(contentBytes);
            encryptedValue = Base64.getEncoder().encodeToString(cipherContent);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return encryptedValue;
    }

    public String decrypt(String encryptedData){
        String decryptedValue;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, decodePrivateKey(Constants.PRIVATE_KEY));
            byte[] cipherContentBytes = Base64.getDecoder().decode(encryptedData.getBytes());
            byte[] decryptedContent = cipher.doFinal(cipherContentBytes);
            decryptedValue = new String(decryptedContent);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return decryptedValue;
    }

    public String encodeKey(Key key) {
        byte[] keyBytes = key.getEncoded();
        String encodedKeyStr = Base64.getEncoder().encodeToString(keyBytes);
        return encodedKeyStr;
  
    }

    public PublicKey decodePublicKey(String keyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.getDecoder().decode(keyStr);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(spec);
        return key;
    }

    public PrivateKey decodePrivateKey(String keyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.getDecoder().decode(keyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        return key;
    }
}
