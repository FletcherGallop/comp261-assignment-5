package nz.comp261.assignment5;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * A new instance of LempelZiv is created for every run.
 */
public class LempelZiv {
	int windowSize = 100;
	
	/**
	 * Take uncompressed input as a text string, compress it, and return it as a
	 * text string.
	 */
	public String compress(String input) {
		int cursor = 0;
		int lookAhead = 0;
		ArrayList<LZTuple> encodedText = new ArrayList<>();
		
		while (cursor < input.length()) {
			
			lookAhead = (cursor + lookAhead >= input.length()) ? input.length() : cursor + lookAhead;
			
			int prevMatch = (cursor - windowSize < 0) ? 0 : cursor - windowSize;
			int matchLength = 1;
			int matchLocation = 0; 
			String str = (cursor == 0) ? "" : input.substring(prevMatch, cursor);
			String nextMatch = input.substring(cursor, cursor + matchLength);
			
			if (str.contains(nextMatch)) { 
				//match found
				matchLength++;
				while (matchLength <= lookAhead) {
					if (cursor + matchLength >= input.length() - 1) {
						matchLength = 1;
						break;
					}
					nextMatch = input.substring(cursor, cursor + matchLength);
					matchLocation = str.indexOf(nextMatch);
					if (matchLength + cursor < input.length() && matchLocation > -1) {
						matchLength++;
					} else {
						break;
					}
				}
				matchLength--;
				matchLocation = str.indexOf(input.substring(cursor, matchLength + cursor));
				cursor += matchLength;
				
				char character = input.charAt(cursor);
				int offset = windowSize - matchLocation;
				
				if ((matchLength + windowSize) >= cursor) {
					offset = cursor - matchLocation - matchLength;
				}
				
				encodedText.add(new LZTuple(offset, matchLength, character));
			} else {
				//new 0 tuple, as no match found
				char character = input.charAt(cursor);
				encodedText.add(new LZTuple(0, 0, character));
			}
			cursor++;
			
		}
		
		
//		System.out.println(encodedText);
		return listToString(encodedText);
	}
//	
//	public byte[] toByteArray(ArrayList<LZTuple> encoded) {
//		ByteBuffer buffer = ByteBuffer.allocate((4 + 4 + 2) * encoded.size());
//		for (LZTuple tuple : encoded) {
//			tuple.addToBuffer(buffer);
//		}
//		return buffer.array();
//	}
	
	public static String listToString(ArrayList<LZTuple> list) {
	    String result = "";
	    for (int i = 0; i < list.size(); i++) {
	        result += "|" + list.get(i);
	    }
	    return result;
	}
	
	/**
	 * Take compressed input as a text string, decompress it, and return it as a
	 * text string.
	 */
	public String decompress(String compressed) {
		ArrayList<LZTuple> compressedTuples = new ArrayList<>();
		
		for (String s : compressed.split("|")) {
			System.out.println(s);
		}
		
		return "";
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't want to. It is called on every run and its return
	 * value is displayed on-screen. You can use this to print out any relevant
	 * information from your compression.
	 */
	public String getInformation() {
		return "";
	}

}

class LZTuple {
	public int offset;
	public int length;
	public char character;
	
	LZTuple(int offset, int length, char character) {
		this.offset = offset;
		this.length = length;
		this.character = character;
	}
	
	@Override
	public String toString() {
		return "[" + this.offset + "|" + this.length + "|" + this.character + "]";
	}
	
	public LZTuple(String tupleString) {
		int indexOfOpen = tupleString.indexOf("[");
		int indexOfEnd = tupleString.lastIndexOf("]");
		
		String removedBrackets = tupleString.substring(indexOfOpen+1, indexOfEnd);
		String[] splitTuple = tupleString.split("|");
		
		this.offset = Integer.parseInt(splitTuple[0]);
		this.length = Integer.parseInt(splitTuple[1]);
		this.character = splitTuple[2].charAt(0);
	
	}
	
	public void addToBuffer(ByteBuffer buffer) {
		buffer.putInt(offset);
		buffer.putInt(length);
		buffer.putChar(character);
	}
}
