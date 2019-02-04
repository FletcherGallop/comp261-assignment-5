package test.comp261.assignment5;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import nz.comp261.assignment5.HuffmanCoding;
import nz.comp261.assignment5.KMP;

class part2Tests {

	@Test
	void basicEncodeTest() {
		String text = "The cat jumped over the rat.";
		HuffmanCoding huff = new HuffmanCoding(text);
		
		String huffEncoded = huff.encode(text);
		
//		System.out.println(huffEncoded);
	}
	
	@Test
	void testEncodeThenDecodeMatch() {
		String text = "The cat jumped over the rat.";
		HuffmanCoding huff = new HuffmanCoding(text);		
		String huffEncoded = huff.encode(text);
		String huffDecoded = huff.decode(huffEncoded);
		
//		System.out.println(huffDecoded);
		assertTrue(text.compareTo(huffDecoded) == 0);
	}
	
	@Test
	void testEncodeThenDecodeMatch2() {
		String text = "Are we human? Or are we dancers? My sign is vital, my hands are cold.";
		HuffmanCoding huff = new HuffmanCoding(text);		
		String huffEncoded = huff.encode(text);
		
		// reduce huffEncoded to byte[]
		
		
		// explode byte[] for huff to decode
		
		String huffDecoded = huff.decode(huffEncoded);
		
//		System.out.println(huffEncoded);
//		System.out.println(huffDecoded);
		assertTrue(text.compareTo(huffDecoded) == 0);
	}
	
	@Test 
	void testEncodeFile() throws UnsupportedEncodingException, IOException {
		String text = new String(Files.readAllBytes(Paths.get("data/war_and_peace.txt")), "UTF-8"); //I think it's this line here, but I don;t know how else to do it. 
		System.out.println(text.length());
		
		HuffmanCoding huff = new HuffmanCoding(text);		
		String huffEncoded = huff.encode(text);
		
		System.out.println(huffEncoded.length());
		
		byte[] foo = stuff.encode(huffEncoded);
		System.out.println(foo.length);
		
	}

	
	static class stuff {
		/**@param input a string of zeroes and ones
		 * @return an array of bytes whose bit string matches the bit string represented by input
		 * @throws IOException */
		static byte[] encode(final String input) throws IOException {
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
		
		static String decode(final byte[] input) {
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
					if(0== --bitsToRecover) {
						// we have recovered everything
						return out.toString();
					}
					theByte <<= 1; // we have read this bit, shift everything up by 1 
				}
			}		
		}
		
	}
	
	
	@Test public void encoder() throws IOException {
		final String input = "01000001"; // 'A'
		final byte[] expected = new byte[] {0,0,0,8,65};		
		
		assertThat(stuff.encode(input), org.hamcrest.core.Is.is(expected));
		
	}
	
	@Test public void encoder2() throws IOException {
		final String input = "0100000101000001"; // 'A'
		final byte[] expected =new byte[] {0,0,0,16,65,65};
		
		
		
		assertThat(stuff.encode(input), org.hamcrest.core.Is.is(expected));
		
	}
	@Test public void decoder() {
		final byte[] input = new byte[] {0,0,0,8,65};
		final String expected = "01000001"; // 'A'
		
		assertThat(stuff.decode(input), org.hamcrest.core.Is.is(expected));
		
	}
	
	@Test public void encodeFunny1() throws IOException {
		final String input = "0101";
		final byte[] expected = new byte[] {0,0,0,4,0b01010000};
		assertThat(stuff.encode(input), org.hamcrest.core.Is.is(expected));
	}
	
	@Test public void decodeFunny() {
		final byte[] input = new byte[] {0,0,0,4,0b01010000};
		final String expected = "0101";
		assertThat(stuff.decode(input), org.hamcrest.core.Is.is(expected));
	}
	
}