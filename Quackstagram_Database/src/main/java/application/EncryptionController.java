package application;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class EncryptionController
{
    private static final String ENCRYPTION_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final String SALT_ALGORITHM = "SHA1PRNG";

    private static final int KEY_LENGTH = 56 * 8; // 512
    private static final int ITERATIONS = 1000;

    public static String encrypt(String value)
    {
        try
        {
            return generateHash(value);
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean validate(String value, String encryptedValue)
    {
        try
        {
            var parts = encryptedValue.split(":");

            var iterations = Integer.parseInt(parts[0]);
            var salt = fromHex(parts[1]);
            var hash = fromHex(parts[2]);

            var spec = new PBEKeySpec(value.toCharArray(), salt, iterations, hash.length * 8);
            var secretKeyFactory = SecretKeyFactory.getInstance(ENCRYPTION_ALGORITHM);
            var testHash = secretKeyFactory.generateSecret(spec).getEncoded();
            var difference = hash.length ^ testHash.length;

            for (var i = 0; i < Math.min(hash.length, testHash.length); i++)
                difference |= hash[i] ^ testHash[i];

            return difference == 0;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static String generateHash(String value) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        var chars = value.toCharArray();
        var salt = generateSalt();

        var spec = new PBEKeySpec(chars, salt, ITERATIONS, KEY_LENGTH);
        var secretKeyFactory = SecretKeyFactory.getInstance(ENCRYPTION_ALGORITHM);

        var hash = secretKeyFactory.generateSecret(spec).getEncoded();
        return ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] generateSalt() throws NoSuchAlgorithmException
    {
        var secureRandom = SecureRandom.getInstance(SALT_ALGORITHM);
        var salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] bytes) throws NoSuchAlgorithmException
    {
        var bigInteger = new BigInteger(1, bytes);
        var hex = bigInteger.toString(16);

        var paddingLength = (bytes.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        var bytes = new byte[hex.length() / 2];
        for (var i = bytes.length - 1; i >= 0; i--)
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        return bytes;
    }
}