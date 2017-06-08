/**
 * Implements RC4 encryption cipher. 
 * @author Group 8
 * @version 5/2/2017
 */
public class RC4 {
	private char[] state;
	private char[] key;
	
	/**
	 * RC4 cipher for encryption.
	 * @param key Key used to encrypt.
	 */
	public RC4(char[] key) {
		state = new char[256];
		this.key = key;
	}
	
	/**
	 * Initializes state. 
	 */
	public void KSA() {
		int j = 0;
		for (int i = 0; i < 256; i++) {
			state[i] = (char) i;
		}
		for (int i = 0; i < 256; i++) {
			j = (j + state[i] + key[i % key.length]) % 256;
			swap(state[i], state[j]);
		}
	}
	
	/**
	 * Swaps elements in state. 
	 * @param i element one
	 * @param j	element two
	 */
	public void swap(char i, char j) {
		char temp = state[j];
		state[j] = i;
		state[i] = temp;
	}
	
	/**
	 * Generates key stream.
	 * @param out Key stream
	 * @param len Length
	 */
	public void PRGA(char[] out, int len) {
		int i = 0;
		int j = 0;
		char t = 0;
		for (int x = 0; x < len; x++) {
			i = (i + 1) % 256;
			j = (j + state[i]) % 256;
			t = state[i];
			state[i] = state[j];
			state[j] = t;
			out[x] = state[(state[i] + state[j]) % 256];
		}
	}
}