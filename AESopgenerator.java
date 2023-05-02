package joelproject1;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class AESopgenerator extends trial {
	
	public static void main(String[] args) throws FileNotFoundException, Exception {
		
		AESopgenerator sb=new AESopgenerator();
    	sb.starter();
        //Scanner scanner = new Scanner(System.in);
        //System.out.print("Enter the key value: ");
        String keyString = "ABCDEF0123456789";

        // Convert the key string to bytes
        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);

        // Create a SecretKeySpec object from the key bytes
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        // Create a Cipher object and initialize it for encryption
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        // Prompt the user to input the plain text to be encrypted
        //System.out.print("Enter the plain text to be encrypted: ");
        String plaintext = trial.intermedcode;

        // Convert the plain text string to bytes
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        // Encrypt the plain text using the Cipher object
        byte[] ciphertextBytes = cipher.doFinal(plaintextBytes);

        // Encode the cipher text as a Base64 string for easy display
        String ciphertext = Base64.getEncoder().encodeToString(ciphertextBytes);
        System.out.println("AES encrytped data: " + ciphertext);
        
        //file generation
        PrintWriter out=new PrintWriter("output.txt");
        out.print(ciphertext);//writing the cipher text into the file
        out.close();
	}

}
