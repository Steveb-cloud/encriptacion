import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String IVKEY = "R3PR353N";
        String EncryptionK = "rpaSPvIvVLlrcmtzPU9/c67Gkj7yL1S5";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to encrypt: ");
        String sTexto = scanner.nextLine();

        try {
            byte[] iv = IVKEY.getBytes(StandardCharsets.US_ASCII);
            byte[] encryptionKey = Base64.getDecoder().decode(EncryptionK);
            byte[] buffer = sTexto.getBytes(StandardCharsets.UTF_8);

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey, "DESede");

            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] encrypted = cipher.doFinal(buffer);
            String encryptedBase64 = Base64.getEncoder().encodeToString(encrypted);
            System.out.println("Encrypted text: " + encryptedBase64);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
