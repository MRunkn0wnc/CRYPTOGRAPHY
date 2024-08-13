import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class DES {
    public static void main(String args[]) throws Exception {
        SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        String originalData = "Hello World";
        String encryptedData = encryptData(originalData, key);
        System.out.println("Encrypted Data: " + encryptedData);
        String decryptedData = decryptData(encryptedData, key);
        System.out.println("Decrypted Data: " + decryptedData);
    }

    private static String encryptData(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    private static String decryptData(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
    }
}
