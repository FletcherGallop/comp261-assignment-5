package nz.comp261.assignment5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {
	private static final int ASCII = 256;
	public String text;
	public Map<String, Character> tree = new TreeMap<>();
	public HuffmanNode rootNode; 
	
	private static class HuffmanNode implements Comparable<HuffmanNode> {
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

		@Override
		public int compareTo(HuffmanNode o) {
			return this.frequency - o.frequency;
		}

		@Override
		public String toString() {
			return "HuffmanNode [character=" + character + ", frequency=" + frequency + ", left=" + left + ", right="
					+ right + "]";
		}
		
		public boolean isLeafNode() { 
			if (((this.left == null) && (this.right == null)) || ((this.left != null) && (this.right != null)));
			return (this.left == null) && (this.right == null);
		}
		
	}
	
	
	/**
	 * This would be a good place to compute and store the tree.
	 */
	public HuffmanCoding(String text) {
		this.text = text;
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {
		//get Char frequency
		int[] freq = new int[ASCII];
		for (int i = 0; i < text.length(); i++) {
			freq[text.toCharArray()[i]]++;
		}
		
		//build tree
		HuffmanNode root = constructTree(freq);
		rootNode = root;
		
		//generate codes
		String[] codes = new String[ASCII];
		generateCodes(codes, root, "");
		
		//output tree
		outputTree(root, codes);
		
		//encode message
		String encoded = new String();
		for (int i = 0; i < text.length(); i++) {
			String code = codes[text.charAt(i)];
			for (int j = 0; j < code.length(); j++) {
				if (code.charAt(j) == '0') {
					encoded += '0';
				} else if (code.charAt(j) == '1') {
					encoded += '1';
				}
			}
		}		
		return encoded;
	}

	
	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		boolean decoded = false;
		String decodedText = new String();

		for (int i = 0; encoded.length() >= i; i++) {
			if (tree.containsKey(encoded.substring(0, i))) {
				decodedText += tree.get(encoded.substring(0, i));
				encoded = encoded.substring(i);
				i = 0;
			}
		}
				
		return decodedText;
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
		
		for (char i= 0; i < ASCII; i++) {
//			System.out.println(frequency[i]);
			if (frequency[i] > 0) {
//				System.out.println(i); //character
				priorityQ.add(new HuffmanNode(i, frequency[i], null, null));
			}
		}
		
		while (priorityQ.size() > 1) {
			HuffmanNode left = priorityQ.poll();
			HuffmanNode right = priorityQ.poll();
			HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency, left, right);
			priorityQ.add(parent);
		}
		
		return priorityQ.poll();
	}
	
	public void generateCodes(String[] codes, HuffmanNode node, String s) {
		if (!node.isLeafNode()) { 
			generateCodes(codes, node.left, s + '0'); //if left then prefix another 0
			generateCodes(codes, node.right, s + '1'); //right gets prefixed with 1
		} else {
			codes[node.character] = s;
			this.tree.put(s, node.character);
		}
	}
	
	private void outputTree(HuffmanNode node, String[] codes) {		
		if (node.isLeafNode()) {
			//print node
//			System.out.println("LEAF char=" + node.character + ", freq=" + node.frequency + ", code=" + codes[node.character]);
			tree.put(codes[node.character], node.character);
		} else {
			outputTree(node.left, codes);
			outputTree(node.right, codes);
		}
	}

	
}
 
