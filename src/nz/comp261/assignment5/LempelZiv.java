package nz.comp261.assignment5;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
		ArrayList<LZTuple> decompressedTuples = new ArrayList<>();
		
////		for (String s : compressed.split("|")) {
////			System.out.println(s);
////		}
//		
//		System.out.println(compressed);
//		
//		String[] splitCompressed = compressed.split("|");
////		System.out.println(splitCompressed[0]);
////		
////		for (int i = 0; i <= splitCompressed.length; i++) {
////			if (splitCompressed[i].compareTo("[") == 0) {
////				System.out.println(splitCompressed[0]);
//////				for (int j = i; j <= splitCompressed.length; j++) {
//////					if (splitCompressed[i].compareTo("]") == 0) {
//////						System.out.println(splitCompressed[i]);
//////						System.out.println(splitCompressed[j]);
//////					}
//////				}
////			}
////		}
		
		List<String> individualTuples = new ArrayList<>(Arrays.asList(compressed.split("\\[")));
		individualTuples.remove(0);
		individualTuples = individualTuples.stream().map(token -> token.replaceAll("\\]\\|?", "")).collect(Collectors.toList());
		final List<LZTuple> tuples = individualTuples.stream().map(token -> token.split("\\|")).map(LZTuple::new).collect(Collectors.toList());
		tuples.forEach(decompressedTuples::add);
		
		StringBuilder output = new StringBuilder();
		
		for (LZTuple tuple : decompressedTuples) {
			if (tuple.length == 0) { 
				output.append(tuple.character);
			} else {
				for (int i = 0; i < tuple.length; i++) {
					output.append(output.charAt(output.length() - tuple.offset));
				}
				output.append(tuple.character);
			}
		}
		
		return output.toString();
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
	
//	public LZTuple(String tupleString) {
//		int indexOfOpen = tupleString.indexOf("[");
//		int indexOfEnd = tupleString.lastIndexOf("]");
//		
//		String removedBrackets = tupleString.substring(indexOfOpen+1, indexOfEnd);
//		String[] splitTuple = tupleString.split("|");
//		
//		this.offset = Integer.parseInt(splitTuple[0]);
//		this.length = Integer.parseInt(splitTuple[1]);
//		this.character = splitTuple[2].charAt(0);
//	
//	}
	
	public LZTuple(String... strings) {
        this(Integer.valueOf(strings[0]), Integer.valueOf(strings[1]), strings[2].charAt(0));
    }
	
	public void addToBuffer(ByteBuffer buffer) {
		buffer.putInt(offset);
		buffer.putInt(length);
		buffer.putChar(character);
	}
}

