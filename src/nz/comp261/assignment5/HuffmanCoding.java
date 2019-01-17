package nz.comp261.assignment5;

import java.util.PriorityQueue;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {
	private static final int ASCII = 256;
	
	private static class HuffmanNode {
		char character;
		int frequency;
		HuffmanNode left;
		HuffmanNode right;
		
		HuffmanNode(char character, int frequency, HuffmanNode left, HuffmanNode right) {
			this.character = character;
			this.frequency = frequency;
			this.left = left;
			this.right = right;
		}
		
		public int compareWith(HuffmanNode node2) { //comparison based on frequency
			return this.frequency - node2.frequency; 
		}
	}
	
	
	/**
	 * This would be a good place to compute and store the tree.
	 */
	public HuffmanCoding(String text) {
		// TODO fill this in.
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {
		//get Char Frequency
		int[] freq = new int[ASCII];
		for (int i = 0; i < text.length(); i++) {
			freq[text.toCharArray()[i]]++;
		}
		
		
		//build tree
		HuffmanNode root = constructTree(freq);
		//generate codes
		//encode message
		
		return "";
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		// TODO fill this in.
		return "";
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print
	 * out the encoding tree.
	 */
	public String getInformation() {
		return "";
	}
	
	public HuffmanNode constructTree(int[] frequency) {
		PriorityQueue<HuffmanNode> priorityQ = new PriorityQueue<HuffmanNode>();
		return null;
	}
	
	
	
	
}
 
