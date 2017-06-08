import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver of simple implementation of RC4 encryption algorithm. 
 * To see cipherText uncomment line:
 * System.out.println(new String(cipherText));
 * @author Group 8
 * @version 5/2/2017
 */
public class RC4Main {
	public static void main(String[] args) throws FileNotFoundException {
		char[] key = {'K', 'e', 'y'};
		char[] keystream = new char[1024];
		int len = 9; // Length of keystream
		
		long startTime = System.currentTimeMillis();
		System.out.println("Reading File...");
		String temp = readFile("King_James_Bible.txt");
		char[] plainText = temp.toCharArray();
		
		System.out.println("Setting up RC4...");
		RC4 cipher = new RC4(key);
		cipher.KSA();
		cipher.PRGA(keystream, len); 
		
		startTime = System.currentTimeMillis();
		System.out.println("Starting RC4 encryption...");
		char[] cipherText = encrypt(plainText, keystream, len); 
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Encryption Complete: " + totalTime + "ms");
		
		System.out.println(new String(cipherText));
		
		/*startTime = System.currentTimeMillis();
		System.out.println("Starting RC4 decryption...");
		char[] decryptText = decrypt(cipherText, keystream, len);
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("Decryption Complete: " + totalTime + "ms");
		
		for (int i = 0; i < 100; i++) {
			System.out.println(decryptText[i]);
		}*/
	}
	
	/**
	 * Reads in given file. 
	 * @param filename Name of the given file. 
	 * @return Returns file as a string. 
	 * @throws FileNotFoundException Exception if file not found. 
	 */
	public static String readFile(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(filename));
		StringBuilder sb = new StringBuilder();
		while (sc.hasNextLine()) {
			sb.append(sc.nextLine());
		}
		sc.close();
		return sb.toString();
	}
	
	/**
	 * Encrypted the given plain text using the keystream. 
	 * @param plainText The plain text to encrypt. 
	 * @param keystream Keystream used to encrypt.
	 * @param len The length of the keystream
	 * @return Returns a char[] of encrypted text.
	 */
	public static char[] encrypt(char[] plainText, char[] keystream, int len) {
		char[] cipherText = new char[plainText.length];
		for (int i = 0; i < plainText.length; i++) {
			cipherText[i] = (char) (plainText[i] ^ keystream[i % len]);
		}
		return cipherText;
	}
	
	/**
	 * 
	 * @param cipherText The ciphertext to decrypt.
	 * @param keystream The keystream to decrypt the text with. 
	 * @param len The length of the keystream
	 * @return A char[] of the decrypted text. 
	 */
	public static char[] decrypt(char[] cipherText, char[] keystream, int len) {
		return encrypt(cipherText, keystream, len);
	}

}
