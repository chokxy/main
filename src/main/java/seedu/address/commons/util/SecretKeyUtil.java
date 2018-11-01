package seedu.address.commons.util;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * Creates a secret key from String and vice-versa
 */
public class SecretKeyUtil {

    /**
     * Creates a secret key
     * @param algorithm used to generate the secret key from {@code KeyGenerator}
     * @return An instance of {@code SecretKey} generated
     */
    public static SecretKey getSecretKey(String algorithm) {
        KeyGenerator keyGenerator = null;

        try {
            keyGenerator = KeyGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyGenerator.generateKey();
    }

    /**
     * Converts a {@code SecretKey} to a {@code String}
     */
    public static String keyToString(SecretKey secretKey) {
        byte[] encoded = secretKey.getEncoded();

        String encodeToString = Base64.getEncoder().encodeToString(encoded);

        return encodeToString;
    }

    public static void saveSecretKey(SecretKey secretKey, String fileName) {
        byte[] keyByte = secretKey.getEncoded();

        try (FileOutputStream out = new FileOutputStream(fileName)) {
            out.write(keyByte);
        } catch (Exception e) {
            System.out.println("Unable to store key file.");
        }
    }

    public static SecretKey readSecretKey(Path fileName) {
        SecretKey secretKey;

        try {
            byte[] keyByte = Files.readAllBytes(fileName);
            secretKey = new SecretKeySpec(keyByte, "AES");
        } catch (Exception e) {
            System.out.println("Unable to use stored key file.");
            secretKey = getSecretKey("AES");
        }
        return secretKey;
    }
}
