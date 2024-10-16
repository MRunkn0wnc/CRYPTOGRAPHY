import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Generate a key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Get the private and public keys
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // Create a Signature object
            Signature signature = Signature.getInstance("SHA256withDSA");

            // Get the message to sign from the user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the message to sign: ");
            String messageToSign = scanner.nextLine();

            // Sign the message
            signature.initSign(privateKey);
            signature.update(messageToSign.getBytes());
            byte[] digitalSignature = signature.sign();
            System.out.println("Digital Signature: " + bytesToHex(digitalSignature));

            // Verify the signature
            System.out.print("Enter the message to verify: ");
            String messageToVerify = scanner.nextLine();
            signature.initVerify(publicKey);
            signature.update(messageToVerify.getBytes());
            boolean isVerified = signature.verify(digitalSignature);
            if (isVerified) {
                System.out.println("Signature verified: Message is authentic.");
            } else {
                System.out.println("Signature verification failed: Message has been tampered with!");
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to convert bytes to hexadecimal string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
