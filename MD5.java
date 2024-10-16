import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static String getMd5Hash(String input) {
        try {
            // Create a MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Generate the hash
            byte[] messageDigest = md.digest(input.getBytes());
            // Convert byte array to BigInteger
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert to hex string
            String hashtext = no.toString(16);
            // Pad with leading zeros to ensure 32 characters
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string to generate its MD5 hash:");
        String input = scanner.nextLine();
        System.out.println("HashCode Generated for the string is: " + getMd5Hash(input));
        scanner.close();
    }
}
