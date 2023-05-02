package joelproject1;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AESdecryptor 
{
	public static String plaintext;

	//String keyString;
	//public void recall
	public void recall() throws Exception, IOException
	{
		//open the output file
		FileReader fr = new FileReader("output.txt");
        Scanner inFile = new Scanner(fr);

        //the key value
        
        String keyString = "ABCDEF0123456789";

        // Convert the key string to bytes
        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);

        // Create a SecretKeySpec object from the key bytes
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        //cipher text to be decrypted
        
        String ciphertext = inFile.nextLine();
        //close the file
        inFile.close();

        // Decode the cipher text from Base64 back to bytes
        byte[] ciphertextBytes = Base64.getDecoder().decode(ciphertext);

        // Create a Cipher object and initialize it for decryption
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        // decrypt the cipher text using the Cipher object
        byte[] plaintextBytes = cipher.doFinal(ciphertextBytes);

        // Convert the plain text bytes to a string and print it
        plaintext = new String(plaintextBytes, StandardCharsets.UTF_8);
        System.out.println("AES decrypted output: " + plaintext);
    }
	

}
