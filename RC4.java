import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
public class main
{
    public static void main(String[] args)throws Exception
    {
        String o="Hello World",k="SecretKey";
        byte[]kb=k.getBytes(StandardCharsets.UTF_8);
        Key key=new SecretKeySpec(kb,"RC4");
        Cipher c=Cipher.getInstance("RC4");
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[]eb=c.doFinal(o.getBytes(StandardCharsets.UTF_8));
        System.out.println("Original:"+o+"\nEncrypted:"+new String(eb,StandardCharsets.UTF_8));
        c.init(Cipher.DECRYPT_MODE,key);
        byte[]db=c.doFinal(eb);
        System.out.println("Decrypted:"+new String(db,StandardCharsets.UTF_8));
    }
}
