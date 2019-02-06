package nz.comp261.assignment5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
	 * @throws IOException 
	 */
	public byte[] encode(String text) throws IOException {
		//get Char frequency
		final byte[] textArray = text.getBytes();// this is unchanging throughout the loop
		int[] freq = new int[ASCII];
		for (int i = 0; i < text.length(); i++) {
//			System.out.println(String.format("index: %s, value: %s", i, (int) textArray[i]));
			freq[textArray[i]+128]++;
		}
		
		//build tree
		HuffmanNode root = constructTree(freq);
		rootNode = root;
		
		//generate codes
		Map<Integer, String> codes = new TreeMap<>();
		generateCodes(codes, root, "");
		
		//output tree
		outputTree(root, codes);
		
		//encode message
		StringBuffer encoded = new StringBuffer();
		
		System.out.println("Before");
		for (int i = 0; i < text.length(); i++) {
			String code = codes.get((int) text.charAt(i));
			if (null != code) {
				encoded.append(code);
			} else {
//				continue;
			}
		}
//		System.out.println("After");
		
		return encodeToBytes(encoded.toString());
	}

	/**@param input a string of zeroes and ones
	 * @return an array of bytes whose bit string matches the bit string represented by input
	 * @throws IOException */
	static byte[] encodeToBytes(final String input) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int l = input.length();
		
		
		
		byte one = (byte)(l   & 0xFF000000);
		byte two = (byte)(l   & 0x00ff0000);
		byte three = (byte)(l & 0x0000ff00);
		byte four = (byte)(l  & 0x000000ff);
		
		baos.write(new byte[] {one, two, three, four});
		
		
		int theByte = 0;
		for (int i=0; i<input.length(); ++i) {
			theByte <<= 1;
			
			if(input.charAt(i) ==  '1') {
				theByte |= 1; // set last bit of theByte to 1
			}
			
			if((i+1)%8==0) {
				// we are on a byte boundary
				baos.write(theByte); // spit it out
				theByte = 0; // start again
			} else if ( i == input.length()-1){
				// we are not on a byte boundary, but are at the end of the input
				theByte <<= (i+1)%8;
				baos.write(theByte); // spit it out
				
			}
			
		}
		
		
		return baos.toByteArray();
	}
	
	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(byte[] encoded) {
		boolean decoded = false;
		String encodedString = decodeFromBytes(encoded);
		String decodedText = new String();

		for (int i = 0; encodedString.length() >= i; i++) {
			if (tree.containsKey(encodedString.substring(0, i))) {
				decodedText += tree.get(encodedString.substring(0, i));
				encodedString = encodedString.substring(i);
				i = 0;
			}
		}
				
		return decodedText;
	}
	
	static String decodeFromBytes(final byte[] input) {
		final StringBuffer out = new StringBuffer();
		
		final ByteArrayInputStream bais = new ByteArrayInputStream(input);
		int one = bais.read();
		int two = bais.read();
		int three = bais.read();
		int four = bais.read();
		int bitsToRecover = (one << 24) | (two << 16) | three << 8 | four;
				
		
		int theByte;
		while (true) {
			theByte = bais.read();
		
			for(int i=0; i<8;++i) {
				int theBit = (theByte & 0b10000000) >> 7;	
				out.append(theBit);
				if( --bitsToRecover == 0) {
					// we have recovered everything
					return out.toString();
				}
				theByte <<= 1; // we have read this bit, shift everything up by 1 
			}
		}		
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
				priorityQ.add(new HuffmanNode((char) (i-128), frequency[i], null, null));
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
	
	public void generateCodes(Map<Integer, String> codes, HuffmanNode node, String s) {
		if (!node.isLeafNode()) { 
			generateCodes(codes, node.left, s + '0'); //if left then prefix another 0
			generateCodes(codes, node.right, s + '1'); //right gets prefixed with 1
		} else {
			codes.put((int) node.character, s);
			this.tree.put(s, node.character);
		}
	}
	
	private void outputTree(HuffmanNode node, Map<Integer, String> codes) {		
		if (node.isLeafNode()) {
			//print node
//			System.out.println("LEAF char=" + node.character + ", freq=" + node.frequency + ", code=" + codes[node.character]);
			tree.put(codes.get((int) node.character), node.character);
		} else {
			outputTree(node.left, codes);
			outputTree(node.right, codes);
		}
	}

	
}
 
